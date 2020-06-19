import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://search.naver.com/search.naver?sm=tab_hty.top&where=image&query=%EC%B2%9C%ED%98%9C%ED%96%A5&oquery=%EB%A0%88%EB%93%9C%ED%96%A5&tqi=UXxevwp0Jywss5J0aIhssssstpC-085163");

		// 자바스크립트 코드 실행
		JavascriptExecutor js = (JavascriptExecutor) driver;

		while (true) { // 모든 데이터를 브라우저 화면에 보여주기
			long height = (long) js.executeScript("return document.body.scrollHeight");
			System.out.println(height);

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(600);

			long height2 = (long) js.executeScript("return document.body.scrollHeight");
			System.out.println(height2);
			if (height == height2)
				break;
		}

		List<WebElement> list = driver.findElements(By.cssSelector(".img_area img"));
		
		Test t = new Test();
		
		for (int i = 0; i < list.size(); i++) {
			WebElement el = list.get(i);
			String src = el.getAttribute("src");
			System.out.println(src);
			
			t.downloadImage(src, i);
		}

		System.out.println("end");
}

	public void downloadImage(String address, int i) {
		try {
			URL imageURL = new URL(address);
			BufferedImage saveImage = ImageIO.read(imageURL);

			ImageIO.write(saveImage, "jpg", new File(i + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
