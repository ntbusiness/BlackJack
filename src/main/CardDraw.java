package main;

// intcard,newintcard,strcardの取得、合計値算出
public class CardDraw {

	public void draw() {
		// 初期化処理
		Main.rand = 0;
		
		// ランダム出力処理
		Main.rand = Math.random() * 13 + 1;
		Main.intcard[Main.i] = (int)Main.rand;
		
		// 変換処理
		switch (Main.intcard[Main.i]) {
		case 1:
			Main.strcard[Main.i] = "「A」";
			Main.newintcard[Main.i] = Main.intcard[Main.i];
			Main.flag = 1;
			break;
		case 11:
			Main.strcard[Main.i] = "「J」";
			Main.newintcard[Main.i] = 10;
			break;
		case 12:
			Main.strcard[Main.i] = "「Q」";
			Main.newintcard[Main.i] = 10;
			break;
		case 13:
			Main.strcard[Main.i] = "「K」";
			Main.newintcard[Main.i] = 10;
			break;
		default:
			Main.strcard[Main.i] = String.valueOf("「" + Main.intcard[Main.i] + "」");
			Main.newintcard[Main.i] = Main.intcard[Main.i];
			break;
		}
		

		
		// 合計算出処理
		if (Main.player == 0) {
			Main.goukeip = Main.goukeip + Main.newintcard[Main.i];
		} else {
			Main.goukeid = Main.goukeid + Main.newintcard[Main.i];
		}
		
	}

}
