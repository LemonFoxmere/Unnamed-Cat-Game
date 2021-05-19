package com.source.framework.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.annotation.Resources;

public class FontHandler {
	public static Font createFont(int style, int size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, Resources.class.getResourceAsStream("/resource/font.otf")).deriveFont(style, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
