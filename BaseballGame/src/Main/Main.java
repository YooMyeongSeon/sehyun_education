package Main;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("------------------------------"); //게임 시작
			System.out.println("야구게임 시작, 랜덤번호가 생성되었습니다.");
			System.out.println("------------------------------");
			
			int game[] = new int[3]; //숫자 배열 준비(3칸) ////////////////////////////////////////////////////////////////////////////////////////////////////
			
			for (int i=0; i!=3; i++) { //숫자 생성 3번 반복
				int num = (int)(Math.random()*10)+1; //1~10까지의 랜덤 숫자 생성
				
				if (num == 10) { //10제외
					i--;
				} else {
					game[i] = num; //배열에 포함
					
					for (int j=0; j<i; j++) { //중복시 다시 진행
						if (game[j] == game[i]) {
							i--;
							break;
						}
					}
				}
			}
			
//			System.out.println("정답 : " + game[0] + "" + game[1] + "" + game[2]);
			
			boolean start = true; //게임 시작 스위치 
			int count = 1; //정답 카운트
			
			while (start) { //숫자 맞추기 ////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("숫자를 3자리 입력하세요 : ");
				String answer = scan.next();
				
				int intAnswer[] = new int[3]; //답 배열 준비
				
				if (answer.length() == 3 && (answer.contains("1") || answer.contains("2") || answer.contains("3") || answer.contains("4") || answer.contains("5") || answer.contains("6") || answer.contains("7") || answer.contains("8") || answer.contains("9"))) {
					intAnswer[0] = Integer.parseInt(answer.substring(0, 1)); //입력받은 값을 하나씩 배열에 넣는다
					intAnswer[1] = Integer.parseInt(answer.substring(1, 2));
					intAnswer[2] = Integer.parseInt(answer.substring(2));
					
					int strike = 0, ball  = 0; //스트라이크, 볼 값 초기화

					for (int i=0; i!=3; i++) { //값 비교
						if (intAnswer[i] == game[i]) strike++; //같은 자리에 동일한 숫자가 있다면 스트라이크가 증가
					}
					
					if (intAnswer[0] == game[1] || intAnswer[0] == game[2] ) ball++; //다른 자리에 동일한 숫자가 있다면 볼 증가
					if (intAnswer[1] == game[0] || intAnswer[1] == game[2] ) ball++;
					if (intAnswer[2] == game[0] || intAnswer[2] == game[1] ) ball++;
					
					if (strike == 3) { //결과 출력, 스트라이크 3개면 정답 ////////////////////////////////////////////////////////////////////////////////////////////////////
						System.out.println("정답입니다!" + count + "번 만에 맞추셨습니다!");
						start = false;
					} else if (strike == 0 && ball == 0) { //스트라이크, 볼이 없다면 아웃
						System.out.println("아웃");
					} else {
						if (strike == 0) { //스트라이크만 없다면 볼
							System.out.println(ball + "볼");
						} else if (ball == 0) { //볼만 없다면 스트라이크
							System.out.println(strike + "스트라이크");
						} else { //둘 다 있다면 둘 다 출력
							System.out.println(ball + "볼, " + strike + "스트라이크");
						}
					}
				} else {
					System.out.println("3자리의 숫자만 입력 가능합니다.");
				}
				
				count++; //카운트 증가
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}