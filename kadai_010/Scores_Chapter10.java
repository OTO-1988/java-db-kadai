package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Scores_Chapter10 {

	public static void main(String[] args) {
		//データベース接続情報
		String url = "jdbc:mysql://localhost/challenge_java";
		String user = "root";
		String pass = "Alice4351Mys@l";
				
		//SQLクエリ
		String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5 ";
		
		try(
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();

		){
			//接続成功時のメッセージ
			System.out.println("データベース接続成功" + con);
			//データの更新するSQLクエリの実行
			System.out.println("レコード更新を実行します");
			int rowCnt = stmt.executeUpdate(sql);
			if(rowCnt > 0) {
				System.out.println(rowCnt + "件のレコードが更新されました");
			} else {
				System.out.println("更新されデータはありませんでした");
			}
			
			//データを取得し、数学→英語の点数が高い順に並び替えるSQLクエリの実行
			String selectSql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			try (ResultSet result = stmt.executeQuery(selectSql)) {
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					int scoreM = result.getInt("score_math");
					int scoreE = result.getInt("score_english");
					System.out.println(result.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + scoreM + "／英語=" + scoreE);
				}
			}
		}catch (SQLException e) {
			System.out.println("データベースエラー：" + e.getMessage());
		}
		
	}

}
