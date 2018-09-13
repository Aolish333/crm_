package nuc.jyg.crm.service.hikari;

import nuc.jyg.crm.model.DataDictionary;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 10:38 2018/9/12.
 */
public interface IDataDictionaryService {

    ArrayList<DataDictionary> getAll();

    ArrayList<DataDictionary> getByKindAndEntryAndValue(String kind, String entry, String value);

    DataDictionary getById(Integer id);

    Integer update(DataDictionary dataDictionary);

    Integer delete(Integer id);

    Integer add(DataDictionary dataDictionary);
}
