package com.bupt.client.segmenter;

import java.util.ArrayList;
import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

public abstract class SegmenterUtils {

	public static final List<Word> seg(String... texts) {
		List<Word> words = new ArrayList<>();
		for (String text : texts) {
			words.addAll(WordSegmenter.seg(text));
		}
		return words;
	}

	public static final List<Word> seg(SegmentationAlgorithm algorithm, String... texts) {
		List<Word> words = new ArrayList<>();
		for (String text : texts) {
			words.addAll(WordSegmenter.seg(text, algorithm));
		}
		return words;
	}

}
