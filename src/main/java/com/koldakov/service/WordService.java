package com.koldakov.service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koldakov.entity.Word;
import com.koldakov.repository.WordRepository;

@Service
public class WordService {
    private static final Logger log = LoggerFactory.getLogger(WordService.class);

    private static final String DB_FILE_PATH = "/home/anton/Documents/DB/WordDB/WordDB.txt";
    
    private List<Word> repeatList;

    @Autowired
    private WordRepository wordRepository;

    public void addWord(Word word) {
        log.info("-> saving word {}", word);
        wordRepository.save(word);
    }

    public List<Word> getAllWords() {
        List<Word> list = wordRepository.findAll();
        log.info("-> words found {}", list.size());

        return list;
    }

    @PostConstruct
    private void loadDb() {
        log.info("-> loading word database");

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_PATH))) {
            String wordLine;
            while ((wordLine = reader.readLine()) != null) {
                String[] wordParts = wordLine.split(";");
                wordRepository.save(new Word(wordParts[0], wordParts[1]));
            }
        } catch (IOException e) {
            log.error("Cannot load database because {}", e.getMessage());
        }
    }

    @PreDestroy
    private void persistDb() {
        log.info("-> persisting word database");

        List<Word> list = wordRepository.findAll();
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(DB_FILE_PATH))) {
            list.forEach(w -> writer.println(wordToDbString(w)));
        } catch (IOException e) {
            log.error("Cannot persist database because {}", e.getMessage());
        }
    }

    private String wordToDbString(Word word) {
        return word.getRussian() + ";" + word.getLatvian();
    }

	public Word getRandomWord() {
		Word word = null;
		
		if (repeatList == null || repeatList.size() == 0) {
			repeatList = wordRepository.findAll();
			Collections.shuffle(repeatList);
		}
		if (repeatList.size() > 0) {
			word = repeatList.get(0);
			repeatList.remove(0);
		}
		
		return word;
	}
}
