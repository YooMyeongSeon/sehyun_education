package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main2 {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) { //메인 메소드에서 게임 시작 ////////////////////////////////////////////////////////////////////////////////////////////////////
		while (true) { //무한 반복으로 동작
			System.out.println("연산자 게임을 시작합니다.");
			System.out.println("--------------------");
			
			int[] score = {0, 3}; //게임 시작시 점수 0점, 기회 3번 초기화
			
			start(score);
		}
	}
	
	static void start(int[] score) { //게임 메소드 ////////////////////////////////////////////////////////////////////////////////////////////////////
		for (int i=0; i!=10; i++) { //문제 10번 반복
			System.out.println((i+1) + "번째 문제입니다, 연산자를 입력하세요 :");
			
			String answer = scan.next();

			if (answer.equals("+") || answer.equals("-") || answer.equals("*") || answer.equals("/")) { //연산자만 입력 가능
				int numA = (int)(Math.random()*10); //0~9까지의 랜덤 숫자 생성
				int numB = (int)(Math.random()*10);
				
				System.out.println("문제 : " + numA + " " + answer + " " + numB + " = ");
				
				try {
					score = operation(answer, numA, numB, score, i);
				} catch (InputMismatchException e) {
					String wrong  = scan.next();
						System.out.println("[" + wrong  + "]은(는) 올바른 입력이 아닙니다. : " + e);
						i--;
					continue;
				} finally {
					System.out.println("--------------------");
				}

				if (score[1] == 0) { //게임 실패 ////////////////////////////////////////////////////////////////////////////////////////////////////
					System.out.println("기회를 모두 소진했습니다, 처음으로 돌아갑니다.\n\n");
					System.out.println("--------------------");
					break;
				}
			} else {
				System.out.println("연산자만 입력 가능합니다.");
				System.out.println("--------------------");
				i--;
			}
		}
		
		if (score[1] != 0) { //10회 게임이 종료된 후 결과 출력, 실패로 끝났다면 생략
			result(score); 
		}
	}
		
	static int[] operation(String answer, int numA, int numB, int[] score, int i) { //연산 메소드 ////////////////////////////////////////////////////////////////////////////////////////////////////
		if (answer.equals("/")) { //나누기 경우의 연산을 따로 처리
			double result = scan.nextDouble();

			if (numA == 0 || numB == 0) { //두 랜덤수 중 하나라도 0일경우를 따로 연산
				if (result == 0) {
					return score = correct(score, i);
				} else {
					return score = incorrect(score);
				}
			} else { //보통의 나누기 연산
				if (result == Math.floor(((double)numA / (double)numB) * 10) / 10) {
					return score = correct(score, i);
				} else {
					return score = incorrect(score);
				}
			}
		} else { //더하기, 빼기, 곱하기 경우의 연산
			int result = scan.nextInt();
			
			if (answer.equals("+")) {
				if (result == (numA + numB)) {
					return score = correct(score, i);
				} else {
					return score = incorrect(score);
				}
			} else if (answer.equals("-")) {
				if (result == (numA - numB)) {
					return score = correct(score, i);
				} else {
					return score = incorrect(score);
				}
			} else {
				if (result == (numA * numB)) {
					return score = correct(score, i);
				} else {
					return score = incorrect(score);
				}
			}
		}
	}
	
	static int[] correct(int[] score, int i) { //정답 메소드 ////////////////////////////////////////////////////////////////////////////////////////////////////
		score[0]++;	
		
		if (i != 9) { //마지막 점수 출력은 생략한다.
			System.out.println("\n정답입니니다, 현재 점수는 " + score[0] + "점 입니다.");
		}
		
		return score;
	}
	
	static int[] incorrect(int[] score) { //오답 메소드 ////////////////////////////////////////////////////////////////////////////////////////////////////
		score[1]--;
		
		if (score[1] != 0) { //마지막 0점 출력은 생략한다.
			System.out.println("\n틀렸습니다, 기회는 " + score[1] + "번 남았습니다.");
		}
		
		return score;
	}
	
	static void result(int[] score) { //결과 메소드 ////////////////////////////////////////////////////////////////////////////////////////////////////
		if (score[0] == 10) {
			System.out.println("10문제를 모두 맞추셨습니다, 고급입니다.\n\n");
		} else if (score[0] == 9) {
			System.out.println("9문제를 맞추셨습니다, 중급입니다.\n\n");			
		} else {
			System.out.println("8문제를 맞추셨습니다, 초급입니다.\n\n");
		}
	}
}