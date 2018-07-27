package com.yidi.Interface;

import com.yidi.entity.DialogueRecord;

public interface SpecialprocessFactory {
	int ageProcess(String text,DialogueRecord lr);
	int sexProcess(String text,DialogueRecord lr);
}
