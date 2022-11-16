package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			boolean start = true; //게임 시작 스위치
			
			while (start) {
				System.out.println("게임을 시작합니다, 연산자를 입력하세요 : "); //게임 시작
				String answer = scan.next();
				
				if (answer.equals("+") || answer.equals("-") || answer.equals("*")) { //연산자만 입력 가능
					int score = 0; //게임 시작시 점수 0점 초기화
					int chance = 3; //기회 3번 초기화
					
					for (int i=0; i!=10; i++) { //문제 10번 반복
						int numA = (int)(Math.random()*10); //0~9까지의 랜덤 숫자 생성
						int numB = (int)(Math.random()*10);
						
						System.out.println((i+1) + "번째 문제입니다.");
						System.out.println(numA + " " + answer + " " + numB + " = ");
						
						int result = 0;
						
						try {
							result = scan.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("올바른 숫자를 입력하지 않아 게임이 종료됩니다.");
							System.out.println("--------------------");
							start = false;
							break;
						} finally {
							if (answer.equals("+")) {
								if (result == (numA + numB)) {score++;} else {chance--;}
							} else if (answer.equals("-")) {
								if (result == (numA - numB)) {score++;} else {chance--;}
							} else {
								if (result == (numA * numB)) {score++;} else {chance--;}
							}
						}
						
						if (chance == 0) {
							System.out.println("기회를 모두 소진했습니다, 처음으로 돌아갑니다.");
							System.out.println("--------------------");
							break;
						}
					}
					
					System.out.println(result(score));
					System.out.println("--------------------");
				} else if (answer.equals("/")) {
					int score = 0; //게임 시작시 점수 0점 초기화
					int chance = 3; //기회 3번 초기화
					
					for (int i=0; i!=10; i++) { //문제 10번 반복
						int numA = (int)(Math.random()*10); //0~9까지의 랜덤 숫자 생성
						int numB = (int)(Math.random()*10);
						
						System.out.println((i+1) + "번째 문제입니다.");
						System.out.println(numA + " / " + numB + " = ");
						
						double result = 0;
						
						try {
							result = scan.nextDouble();
						} catch (InputMismatchException e) {
							System.out.println("올바른 숫자를 입력하지 않아 게임이 종료됩니다.");
							System.out.println("--------------------");
							start = false;
							break;
						} finally {
							if (numA == 0 || numB == 0) {
								if (result == 0) {score++;} else {chance--;}
							} else {
								double realResult = Math.floor(((double)numA / (double)numB) * 10) / 10;
								if (result == realResult) {score++;} else {chance--;}
							}
						}
						
						if (chance == 0) {
							System.out.println("기회를 모두 소진했습니다, 처음으로 돌아갑니다.");
							System.out.println("--------------------");
							break;
						}
					}
					
					System.out.println(result(score));
					System.out.println("--------------------");
				} else {
					System.out.println("연산자만 입력 가능합니다.");
					System.out.println("--------------------");
				}
			}
		}
	}
	
	public static String result(int score) { //결과값 함수
		if (score == 10) {
			return "10문제를 모두 맞추셨습니다, 고급입니다.";
		} else if (score == 9) {
			return "9문제를 맞추셨습니다, 중급입니다.";			
		} else {
			return "8문제를 맞추셨습니다, 초급입니다.";
		}
	}
}