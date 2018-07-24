package com.wangqifan.toutiao.Service;


import com.wangqifan.toutiao.DAO.NewsDAO;
import com.wangqifan.toutiao.Models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsDAO newsdao;
    public List<News> getLatestNews(int userId, int offset, int limit) {
        return newsdao.selectByUserIdAndOffset(userId, offset, limit);
    }
    public News getById(int id)
    {
        return newsdao.selectbyId(id);
    }

    public int addNews(News news)
    {
        return newsdao.addNews(news);
    }
    public  int  updateCommentCount(int id,int count)
    {
       return newsdao.updateCommentCount(id,count);
    }

    public int updateLikeCount(int id, int count) {
        return newsdao.updateLikeCount(id, count);
    }

}
