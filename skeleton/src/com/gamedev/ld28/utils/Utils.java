package com.gamedev.ld28.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.gamedev.ld28.Assets;

public class Utils {

	private static final String symbols = ",.!?'\"-+=/\\%()<>:;";
	public static enum EStringJustify { LEFT, CENTER, RIGHT };
	private static boolean[] lastInput = new boolean[256];
	private static int pixelsBetweenCharacters = 2; // spacing between characters (pixels)
	
	public static void updateInput(){
		for (int i =0; i < 256; i++){
			lastInput[i] = Gdx.input.isKeyPressed(i);
		}
	}

	public static boolean isKeyJustPressed(int key){
		return (!lastInput[key] && Gdx.input.isKeyPressed(key));
	}
	
	public static void drawText(String text, float x, float y, Color color, EStringJustify justify)
	{
		drawText(text, x, y, Config.textWidth, Config.textHeight, color, justify);
	}
	
	public static void drawText(String text, float x, float y, int w, int h, Color color, EStringJustify justify) {
		Assets.batch.begin();
		drawText(Assets.batch, text, x, y, w, h, color, justify);
		Assets.batch.end();
	}
	
	public static void drawText(SpriteBatch batch, String text, float x, float y, Color color, EStringJustify justify) {
		drawText(batch, text, x, y, Config.textWidth, Config.textHeight, color, justify);
	}

//	public static void drawText(SpriteBatch batch, String text, float x, float y, Color color, EStringJustify justify, boolean wrap) {
//		if(wrap)
//		{
//			//If there is more text than will fit on one line
//			if(text.length() * (Config.textWidth + pixelsBetweenCharacters) > batch.g)
//			{
//				int breakpoint = 0;
//				int originalLength = text.length();
//				
//				while(breakpoint != -1 && breakpoint < originalLength)
//				{
//					breakpoint = text.lastIndexOf(' ', Config.textWidth);
//					//TODO:   build this up into a single print
//					drawText(batch, text.substring(0, breakpoint), x, y, Config.textWidth, Config.textHeight, color, justify);
//					y = y - Config.textHeight - pixelsBetweenCharacters;
//					text = text.substring(breakpoint + 1);
//				}
//			}
//		}
//		else{
//			//Just draw as much of the text as will fit
//			drawText(batch, text, x, y, Config.textWidth, Config.textHeight, color, justify);
//		}
//	}

	public static void drawText(SpriteBatch batch, String text, float x, float y, int w, int h, Color color, EStringJustify justify) {
		text = text.toUpperCase();
		
		batch.setColor(color);
		float totalWidth = text.length() * w;
		switch (justify)
		{
		case LEFT:
			break;
		case CENTER:
			x -= totalWidth/2;
			break;
		case RIGHT:
			 x-= totalWidth;
			break;
		}
		for(int i = 0; i < text.length(); ++i) {
			char ch = text.charAt(i);
			float xPos = x + i * w + pixelsBetweenCharacters;
			float yPos = y;

			if (ch >= 'A' && ch <= 'Z') {
				batch.draw(Assets.letters[0][ch - 'A'], xPos, yPos, w, h);
			} else if (ch >= '0' && ch <= '9') {
				batch.draw(Assets.digits[0][ch - '0'], xPos, yPos, w, h);
			} else {
				int index = symbols.indexOf(ch);
				if (index != -1) {
					batch.draw(Assets.symbols[0][index], xPos, yPos, w, h);
				}
			}
		}
		batch.setColor(Color.WHITE);
	}

	public static Rectangle inflate(Rectangle bounds, float width, float height) {
		return new Rectangle(bounds.x - width/2, bounds.y - height/2,
				bounds.width + width, bounds.height + height);
	}

	/**
	 * 1-100
	 * @param prob
	 * @return
	 */
	public static boolean probability(int prob) {
		return (Config.rand.nextInt(100) < prob);
	}	
}
