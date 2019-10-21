package com.haipiao.articleservice.handler;

import com.haipiao.articleservice.dto.common.Tag;
import com.haipiao.articleservice.dto.common.Topic;
import com.haipiao.articleservice.dto.req.CreateArticleRequest;
import com.haipiao.articleservice.dto.req.GetArticleRequest;
import com.haipiao.articleservice.dto.resp.CreateArticleResponse;
import com.haipiao.articleservice.dto.resp.GetArticleResponse;
import com.haipiao.persist.config.PersistConfig;
import com.haipiao.persist.entity.User;
import com.haipiao.persist.repository.*;
import org.assertj.core.util.Preconditions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = {ArticleHandlerTest.Config.class, PersistConfig.class})
public class ArticleHandlerTest {

    @Configuration
    static class Config {
        @Autowired
        private UserRepository userRepository;

        @Bean
        public CreateArticleHandler createArticleHandler(
                @Autowired ArticleRepository articleRepository,
                @Autowired ImageRepository imageRepository,
                @Autowired TopicRepository topicRepository,
                @Autowired ArticleTopicRepository articleTopicRepository,
                @Autowired TagRepository tagRepository) {
            return new CreateArticleHandler(
                    articleRepository,
                    topicRepository,
                    articleTopicRepository,
                    imageRepository,
                    tagRepository);
        }

        @Bean
        public GetArticleHandler getArticleHandler(
                @Autowired ArticleRepository articleRepository,
                @Autowired UserRepository userRepository,
                @Autowired ImageRepository imageRepository,
                @Autowired TopicRepository topicRepository,
                @Autowired ArticleTopicRepository articleTopicRepository,
                @Autowired TagRepository tagRepository) {
            return new GetArticleHandler(
                    articleRepository,
                    userRepository,
                    topicRepository,
                    articleTopicRepository,
                    imageRepository,
                    tagRepository);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateArticleHandler createArticleHandler;

    @Autowired
    private GetArticleHandler getArticleHandler;

    private int userId;

    @Before
    public void setUp() {
        Preconditions.checkNotNull(createArticleHandler);
        Preconditions.checkNotNull(getArticleHandler);

        User user = new User();
        user.setUserName("blah");
        user = userRepository.save(user);
        this.userId = user.getUserId();
    }

    @Test
    public void testCreateGetArticleHandler() {
        CreateArticleRequest createReq = new CreateArticleRequest();
        createReq.setAuthorId(userId);
        String title = "This is a Title";
        createReq.setTitile(title);
        String textBody = "This text body.";
        createReq.setText(textBody);
        Topic[] topics = new Topic[3];
        for (int i=0; i<topics.length; i++) {
            topics[i] = new Topic();
            topics[i].setName("this is topic"+i);
        }
        createReq.setTopics(topics);
        CreateArticleRequest.Image[] images = new CreateArticleRequest.Image[3];
        for (int i=0; i<images.length; i++) {
            images[i] = new CreateArticleRequest.Image();
            images[i].setExternalUrl("https://foo.com/bar"+i);
            images[i].setHashDigest(("hashdigest"+i).getBytes());
            Tag[] tags = new Tag[3];
            for (int j=0; j<tags.length; j++) {
                tags[j] = new Tag();
                tags[j].setText("this is tag"+j);
                tags[j].setX(40+j);
                tags[j].setY(50+j);
            }
            images[i].setTags(tags);
        }
        createReq.setImages(images);

        CreateArticleResponse createResp = createArticleHandler.handle(createReq);
        assertTrue(createResp.getSuccess());
        assertNotNull(createResp.getData().getId());

        int id = createResp.getData().getId();
        GetArticleRequest getReq = new GetArticleRequest();
        getReq.setId(id);
        GetArticleResponse getResp = getArticleHandler.handle(getReq);
        assertTrue(getResp.getSuccess());
        assertNotNull(getResp.getData().getAuthor());
        GetArticleResponse.Data.Image[] actualImages = getResp.getData().getImages();
        assertEquals(images.length, actualImages.length);
        for (int i = 0; i < images.length; i++) {
            assertTrue(actualImages[i].getUrl().startsWith(images[i].getExternalUrl()));
            Tag[] actualTags = actualImages[i].getTags();
            for (int j=0; j<actualTags.length; j++) {
                assertEquals(actualTags[j].getText(), "this is tag"+j);
                assertEquals(actualTags[j].getX(), 40+j);
                assertEquals(actualTags[j].getY(), 50+j);
            }
        }
        Topic[] actualTopics = getResp.getData().getTopics();
        assertEquals(topics.length, actualTopics.length);
        for (int i = 0; i < topics.length; i++) {
            assertEquals(actualTopics[i].getName(), topics[i].getName());
        }
        assertEquals(title, getResp.getData().getTitle());
        assertEquals(textBody, getResp.getData().getText());
        assertEquals(0, getResp.getData().getShares());
        assertEquals(0, getResp.getData().getLikes());
        assertEquals(0, getResp.getData().getCollects());
    }
}
