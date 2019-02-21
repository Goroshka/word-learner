package com.koldakov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koldakov.entity.Word;
import com.koldakov.service.WordService;

@Controller
public class WordController {
	
	private final WordService wordService;
	
	@Autowired
	public WordController(WordService wordService) {
		this.wordService = wordService;
	}
	
	@GetMapping("/wordadd")
	public String wordAdd() {
		return "wordadd";
	}
	
	@PostMapping("/wordadd")
	public String wordAdd(Word word) {
		wordService.addWord(word);
		return "wordadd";
	}
	
	@GetMapping("/wordlist")
	public String wordList(Model model) {
		model.addAttribute("wordList", wordService.getAllWords());
		return "wordlist";
	}
	
	@GetMapping("/wordrepeat")
	public String wordRepeat(Model model) {
		model.addAttribute("word", wordService.getRandomWord());
		return "wordrepeat";
	}
	
	@PostMapping("/wordrepeat")
	public String wordRepeatSubmit(Model model) {
		model.addAttribute("word", wordService.getRandomWord());
		return "wordrepeat";
	}
}
