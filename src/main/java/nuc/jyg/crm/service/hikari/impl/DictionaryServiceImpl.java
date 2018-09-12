package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.dao.DataDictionaryMapper;
import nuc.jyg.crm.model.DataDictionary;
import nuc.jyg.crm.service.hikari.IDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 10:39 2018/9/12.
 */
@Service(value = "iDataDictionaryService")
public class DictionaryServiceImpl implements IDataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public ArrayList<DataDictionary> getAll() {
        return dataDictionaryMapper.selectAll();
    }

    @Override
    public ArrayList<DataDictionary> getByKindAndEntryAndValue(String kind, String entry, String value) {
        if (StringUtils.equals(kind, "")) {
            kind = null;
        }
        if (StringUtils.equals(entry, "")) {
            entry = null;
        }
        if (StringUtils.equals(value, "")) {
            value = null;
        }
        return dataDictionaryMapper.selectByKindAndEntryAndValue(kind, entry, value);
    }

    @Override
    public DataDictionary getById(Integer id) {
        return dataDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer update(DataDictionary dataDictionary) {
        return dataDictionaryMapper.updateByPrimaryKeySelective(dataDictionary);
    }

    @Override
    public Integer delete(Integer id) {
        return dataDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer add(DataDictionary dataDictionary) {
        return dataDictionaryMapper.insert(dataDictionary);
    }
}
