package com.neo.service;

import com.neo.model.Neo;
import com.neo.persistence.NEODao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class NEOService {

    private NEODao NEODao;

    public NEOService(NEODao NEODao) {
        this.NEODao = NEODao;
    }

    public Neo save(Neo neo){
        return NEODao.save(neo);
    }

    public Neo findById(Integer id){
        return NEODao.findById(id);
    }

    public List<Neo> loadAll(int page, int limit){
        return NEODao.findAll(new PageRequest(page - 1, limit, Sort.Direction.ASC, "name")).getContent();
    }
}
