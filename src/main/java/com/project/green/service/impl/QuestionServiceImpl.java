package com.project.green.service.impl;

import com.project.green.dao.QuestionDao;
import com.project.green.dao.TopicDao;
import com.project.green.dto.AnswerDto;
import com.project.green.dto.QuestionDto;
import com.project.green.dto.TopicDto;
import com.project.green.entities.Answer;
import com.project.green.entities.Question;
import com.project.green.entities.Topic;
import com.project.green.exception.EntityNotFoundException;
import com.project.green.mapper.QuestionMapper;
import com.project.green.service.AnswerService;
import com.project.green.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerService answerService;

    @Transactional
    @Override
    public void save(QuestionDto questionDto) {
        if (questionDao.getByValue(questionDto.getQuestionValue()).isPresent()) {
            throw new IllegalArgumentException("Question usually is present");
        }
        questionDao.save(questionMapper.toQuestionEntity(questionDto));
    }

    @Transactional
    @Override
    public QuestionDto getByValue(String value) {
        Optional<Question> question = questionDao.getByValue(value);
        if(question.isPresent()) {
            return questionMapper.toQuestionDto(question.get());
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Transactional
    @Override
    public QuestionDto update(QuestionDto questionDto) {
        if (questionDao.getById(questionDto.getId()).isPresent()) {
            return questionMapper.toQuestionDto(questionDao.update(questionMapper.toQuestionEntity(questionDto)).get());
        }else {
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Override
    public List<QuestionDto> getAll() {
        Optional<List<Question>> questions = questionDao.getAll();
        if(questions.isPresent()) {
            return questions.get().stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
        }else {
            throw new EntityNotFoundException("Questions not found");
        }
    }

    @Override
    public QuestionDto getById(int id) {
        Optional<Question> question = questionDao.getById(id);
        if(question.isPresent()) {
            return questionMapper.toQuestionDto(question.get());
        }else{
            throw new EntityNotFoundException("Question not found");
        }
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        questionDao.delete(id);
    }

    @Override
    public List<QuestionDto> findAllByTopicId(int id) {
        Optional<Topic> topic = topicDao.getTopicById(id);
        if(!topic.isPresent()){
            throw new EntityNotFoundException("Topic is not found");
        }
        Optional<List<Question>> questions = questionDao.getAllQuestionsByTopicId(id);
        if (questions.isPresent()) {
            return questions.get().stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
        }else{
            throw new EntityNotFoundException(String.format("Not found questions for topic is %s" , topic.get().getTitle()));
        }
    }

    @Override
    public List<TopicDto> countQuestionsByTopic() {
        Optional<List<String>> result = questionDao.countQuestionByTopic();
        if (result.isPresent()) {
            List<TopicDto> topicDto = new ArrayList<>();
            for (int i = 0; i < result.get().size(); ) {
                TopicDto dto = new TopicDto();
                dto.setTitle(result.get().get(i));
                dto.setCountQuestion(Integer.parseInt(result.get().get(i + 1)));
                topicDto.add(dto);
                i = i + 2;
            }
            return topicDto;
        } else {
            throw new EntityNotFoundException("Topic is not present");
        }
    }

    @Override
    public boolean addAnswerToQuestion(int id, String answerText) {
        List<AnswerDto> allAnswers = answerService.getAllAnswersToQuestionInOrderByVoice(id);
        QuestionDto question = getById(id);

        if (allAnswers.stream().map(AnswerDto::getAnswerText).collect(Collectors.toSet()).contains(answerText)) {
            return  false;
        }
        Answer answer = new Answer();
        answer.setVoiceCount(0);
        answer.setAnswerText(answerText);
        answer.setDefault(false);
        answer.setQuestion(questionMapper.toQuestionEntity(question));
        questionDao.addAnswerToQuestion(id, answer);
        return  true;
    }
}
