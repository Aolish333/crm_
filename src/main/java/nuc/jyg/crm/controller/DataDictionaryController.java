package nuc.jyg.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import nuc.jyg.crm.model.DataDictionary;
import nuc.jyg.crm.service.hikari.IDataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 21:54 2018/9/11.
 */
@Log4j2
@Controller
@RequestMapping(value = "/data")
public class DataDictionaryController {

    @Autowired
    private IDataDictionaryService iDataDictionaryService;

    @GetMapping(value = "/edit")
    public String getDataDictionaryEdit(Integer id, Model model) {
        DataDictionary dataDictionarie = iDataDictionaryService.getById(id);
        model.addAttribute("dataDictionarie", dataDictionarie);
        return "system-dictionary-edit";
    }

    @GetMapping(value = "/update")
    public String updateDataDictionary(DataDictionary dataDictionary, Model model) {
        iDataDictionaryService.update(dataDictionary);
        ArrayList<DataDictionary> dataDictionaries = iDataDictionaryService.getAll();
        model.addAttribute("dataDictionaries", dataDictionaries);
        return "system-dictionary";
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public String deleteDataDictionary(Integer id, Model model) throws JsonProcessingException {
        iDataDictionaryService.delete(id);
        ArrayList<DataDictionary> dataDictionaries = iDataDictionaryService.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(new Integer(1));
    }

    @GetMapping(value = "/dictionarys")
    public String getDataDictionarys(Model model) {
        ArrayList<DataDictionary> dataDictionaries = iDataDictionaryService.getAll();
        model.addAttribute("dataDictionaries", dataDictionaries);
        return "system-dictionary";
    }

    @GetMapping(value = "/dictionary/search")
    public String getDataDictionary(String kind, String entry, String value, Model model) {
        ArrayList<DataDictionary> dataDictionaries = iDataDictionaryService.getByKindAndEntryAndValue(kind.trim(),
                entry.trim(), value.trim
                        ());
        model.addAttribute("dataDictionaries", dataDictionaries);
        return "system-dictionary";
    }

    @GetMapping(value = "/page/add")
    public String pageAdd(Model model) {
        return "system-dictionary-add";
    }

    @GetMapping(value = "/add")
    public String add(String checkbox, DataDictionary dataDictionary, Model model) {
        byte editable = 0;
        if (StringUtils.equals(checkbox, "on")) {
            dataDictionary.setEditable(editable);
        } else {
            editable = 1;
            dataDictionary.setEditable(editable);
        }
        iDataDictionaryService.add(dataDictionary);
        return "redirect:/data/dictionarys";
    }
}
