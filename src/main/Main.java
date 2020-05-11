package main;

import java.util.Arrays;

public class Main {

	static int goukeip;		// プレイヤー合計
	static int goukeid;		// ディーラー合計
	static int player;		//「0：プレイヤー」 「1：ディーラー」
	static int i;				// ポインタ
	static int x;
	static double rand;		// 1～13のランダム数値
	static int flag;			// 「A」フラグ
	static int intcard[] = new int[11];			// ランダム数値のint型
	static int newintcard[] = new int[11];		// 変換後の数値
	static String strcard[] = new String[11];		// 文字列として格納
	static CardDraw card = new CardDraw();	// 配列を10確保
	static int goukeipA;		// 「A」考慮版のプレイヤー合計
	static int goukeidA;		// 「A」考慮版のディーラー合計
	static String strmerge;	// 手札詳細表示用
	
	public static void main(String[] args) {
		// 初期化処理
		int win = 0;		// 勝利パターン(1 = 「プレイヤーの勝利」 2 = 「ディーラーの勝利」 3 = 「引き分け」)
		int stand = 0;		// スタンド可否判定用
		int brkjkp = 0;	// プレイヤーのブラックジャック判定
		int overp = 0;		// プレイヤーのバスト判定
		flag = 0;
		goukeip = 0;
		goukeipA = 0;
		player = 0;
		i = 0;
		x = 0;
		Arrays.fill(intcard, 0);
		Arrays.fill(newintcard, 0);
		Arrays.fill(strcard, "");
		
		// 1枚目のカードを引く
		card = new CardDraw();
		card.draw();

		// 2枚目のカードを引く
		i++;
		card = new CardDraw();
		card.draw();
				
		// 手札表示
		handcard();
		
		// ブラックジャック判定
		if (goukeipA == 21) {
			brkjkp = 1;
		}
		if (brkjkp == 1) {
			System.out.println("");
			System.out.println("ブラックジャックが成立しました！");
			stand = 2;
		}
		
		while (stand == 0 || stand == 1) {
			System.out.println("");
			System.out.println("ヒットしますか？スタンドしますか？");
			System.out.println("1:ヒット　2:スタンド");
			String hsstr = new java.util.Scanner(System.in).nextLine();
			stand = Integer.parseInt(hsstr);
			if (stand == 1) {
				i++;
				card = new CardDraw();
				card.draw();
				handcard();
				if (goukeip > 21) {
					overp = 1;
					stand = 2;
					System.out.println("バストしました…");
				}
				if (i == 10) {
					stand = 2;
					System.out.println("ヒット上限に達しました。");
				}
			}
			
		}
		if (brkjkp == 1) {
			System.out.println("このまま勝負へ挑みます");
		} else {
			System.out.println("勝負へ挑みます");
		}
		
		if (goukeipA <= 21) {
			if (goukeip < goukeipA) {
				goukeip = goukeipA;
			}
		}
		
		System.out.println("");
		System.out.println("ディーラーが手札を揃えます。");
		
		// ディーラー初期化処理
		int fin = 0;
		int brkjkd = 0;
		int overd = 0;
		flag = 0;
		goukeid = 0;
		goukeidA = 0;
		player = 1;
		i = 0;
		Arrays.fill(intcard, 0);
		Arrays.fill(newintcard, 0);
		Arrays.fill(strcard, "");
		
		// 1枚目のカードを引く
		card = new CardDraw();
		card.draw();
		
		// 2枚目のカードを引く
		i++;
		card = new CardDraw();
		card.draw();
		
		handcard();
		
		// ブラックジャック成立判定
		if (goukeidA == 21) {
			fin = 1;
			brkjkd = 1;
		}
		
		// ディーラーのルールとして合計が17を超えるまでカードを引く
		while (fin == 0) {
			i++;
			card = new CardDraw();
			card.draw();
			handcard();
			if (goukeid > 17) {
				fin = 1;
			}
			if (goukeid > 21) {
				overd = 1;
				fin = 1;
			}
			if (i == 10) {
				fin = 1;
				System.out.println("ディーラーのヒット上限に達しました。");
			}
			
		}
		
		// ディーラーの手札詳細
		strmerge = "";
		if (goukeidA <= 21) {
			if (goukeid < goukeidA) {
				goukeid = goukeidA;
			}
		}
		while (i + 1 > x) {
			strmerge = strmerge + strcard[x];
			x++;
		}
		System.out.println("ディーラーの手札は" + strmerge + "です");
		System.out.println("ディーラーの手札合計は「" + goukeid + "」です");
		System.out.println("");
		
		// 勝敗判定
		System.out.println("プレイヤー合計「" + goukeip + "」vs ディーラー合計「" + goukeid + "」");
		if(brkjkp == 1 || brkjkd == 1) {
			if(brkjkp == 1 && brkjkd == 0) {
				win = 1;
			} else if(brkjkp == 0 && brkjkd == 1) {
				win = 2;
			} else {
				win = 3;
			}
		} else if(overp == 1 || overd == 1) {
			if(overp == 1 && overd == 0) {
				win = 2;
			} else if(overp == 0 && overd == 1) {
				win = 1;
			} else {
				win = 3;
			}
		} else {
			if(goukeip > goukeid) {
				win = 1;
			} else if(goukeip < goukeid) {
				win = 2;
			} else {
				win = 3;
			}
		}		
		switch (win) {
		case 1:
			System.out.println("結果：プレイヤーの勝利！");
			break;
		case 2:
			System.out.println("結果：プレイヤーの敗北…");
			break;
		case 3:
			System.out.println("結果：引き分け");
			break;
		}
		// ＰＧＭ終了
		
		
	}
	public static void handcard() {
		x = 0;
		if (player == 0) {
			strmerge = "";
			while (i + 1 > x) {
				strmerge = strmerge + strcard[x];
				x++;
			}
			System.out.println("あなたの手札は" + strmerge + "です");
			if (flag == 1) {
				goukeipA = goukeip + 10;
				if (goukeipA <= 21) {
					System.out.println("手札合計は「" + goukeip + "」または「" + goukeipA + "」です。");	
				} else {
					System.out.println("手札合計は「" + goukeip + "」です");
				}
			} else {
				System.out.println("手札合計は「" + goukeip + "」です");
			}
		} else if (flag == 1) {
			goukeidA = goukeid + 10;
		}
		
	}
	
}

