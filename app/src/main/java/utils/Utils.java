package utils;

import java.util.List;
import java.util.Random;

import entity.Cell;

public class Utils {

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static void showMessage(String message) {
		System.out.println(message);
	}

	public static String showListCell(List<Cell> listCell) {
		String list = "";
		for (Cell cell : listCell) {
			list = list.concat(showCell(cell) + " ");
		}
		return list;
	}

	public static String showCell(Cell cell) {
		return cell.getX() + "," + cell.getY();
	}

}
