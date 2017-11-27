package cn.tedu.ssh.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ValidateImageAction {
	private InputStream imageStream;

	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	public String execute() {
		System.out.println("ValidateImageAction.execute()");
		try {
			// 初始化变量
			Random random = new Random();
			int w = 200;
			int h = 50;
			// 创建图片
			BufferedImage image = 
					new BufferedImage(
							w, h, BufferedImage.TYPE_INT_RGB);
			// 获取画笔
			Graphics2D g = image.createGraphics();
			// 填充背景颜色
			g.setColor(getRandomColor());
			g.fillRect(0, 0, w, h); // 绘制左上角(0,0)到右下角(w,h)的矩形
			// 随机画200个点
			for (int i = 0; i < 200; i++) {
				image.setRGB(
						random.nextInt(w), 
						random.nextInt(h), 
						0xffffff);
			}
			// 随机画5-10根随机颜色的线条
			for (int i = 0; i < random.nextInt(6) + 5; i++) {
				g.setColor(getRandomColor());
				g.drawLine(
						random.nextInt((int) (w * 0.2f)), 
						random.nextInt(h),
						random.nextInt((int) (w * 0.2f)) 
							+ (int) (w * 0.8f), 
						random.nextInt(h));
			}
			// 画上随机字符串 -- 获取随机字符串
			String validateString = getValidateString();
			// 画上随机字符串 -- 计算
			g.setFont(
					new Font(null, Font.BOLD | Font.ITALIC, 24));
			g.setColor(Color.BLACK);
			FontMetrics fontMetrics = g.getFontMetrics();
			int stringWidth 
				= fontMetrics.stringWidth(validateString);
			int x = (w - stringWidth) / 2;
			int fontHeight 
				= fontMetrics.getAscent() 
					+ fontMetrics.getDescent();
			int y = (h - fontHeight) / 2 
					+ fontMetrics.getAscent();
			// 画上随机字符串 -- 绘制
			g.drawString(validateString, x, y);
			// ----- 至此，绘制完成 -----
			// -------------------------
			ByteArrayOutputStream out = new ByteArrayOutputStream(); // 创建输出流，用于将图片转换为byte[]
			ImageIO.write(image, "png", out); // 把图片写到输出流中
			byte[] buf = out.toByteArray(); // 通过输出流得到byte[]
			out.close();
			setImageStream(new ByteArrayInputStream(buf));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Color getRandomColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r, g, b);
	}

	private String getValidateString() {
		Random random = new Random();
		String source = "ABCDEFGHJKMNPQRSTWXY3456789";
		StringBuffer result = new StringBuffer();
		while (result.length() < 6) {
			result.append(source.charAt(random.nextInt(source.length())));
		}
		return result.toString();
	}

}
