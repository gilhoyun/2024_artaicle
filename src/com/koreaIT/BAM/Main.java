package com.koreaIT.BAM;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

	static int lastArticleId;
	static List<Article> articles;

	static {
		lastArticleId = 0;
		articles = new ArrayList<>();
	}

	public static void main(String[] args) {
		
		System.out.println("프로그램 시작");

		Scanner sc = new Scanner(System.in);

		makeTestData();

		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {

				System.out.printf("제목: ");
				String title = sc.nextLine();
				System.out.printf("내용: ");
				String body = sc.nextLine();

				lastArticleId++;

				Article article = new Article(lastArticleId, body, title, body);

				articles.add(article);

				System.out.println(lastArticleId + "번 게시글이 생성되었습니다.");

			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> printArticles = articles;

				if (searchKeyword.length() > 0) {


					printArticles = new ArrayList<>();

					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
				}

				System.out.println("번호	|	제목	|	작성일	");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	|	%s	|	%s	\n", article.id, article.title, article.regDate);
				}

			} else if (cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}
			
				
			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				
				
				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
					}
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
			

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				articles.remove(foundArticle);

				System.out.println(id + "번 게시물이 삭제되었습니다.");
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}

		}

		sc.close();

		System.out.println("==== 프로그램 종료 ====");

	}

	private static void makeTestData() {
		System.out.println("테스트데이터");
		for (int i = 1; i < 4; i++) {
			articles.add(new Article(++lastArticleId, "regDate", "제목i", "내용i"));
		}

	}

}

class Article {
	int id;
	String regDate;
	String title;
	String body;

	Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;

	}

}
