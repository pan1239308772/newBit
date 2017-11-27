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
			// ��ʼ������
			Random random = new Random();
			int w = 200;
			int h = 50;
			// ����ͼƬ
			BufferedImage image = 
					new BufferedImage(
							w, h, BufferedImage.TYPE_INT_RGB);
			// ��ȡ����
			Graphics2D g = image.createGraphics();
			// ��䱳����ɫ
			g.setColor(getRandomColor());
			g.fillRect(0, 0, w, h); // �������Ͻ�(0,0)�����½�(w,h)�ľ���
			// �����200����
			for (int i = 0; i < 200; i++) {
				image.setRGB(
						random.nextInt(w), 
						random.nextInt(h), 
						0xffffff);
			}
			// �����5-10�������ɫ������
			for (int i = 0; i < random.nextInt(6) + 5; i++) {
				g.setColor(getRandomColor());
				g.drawLine(
						random.nextInt((int) (w * 0.2f)), 
						random.nextInt(h),
						random.nextInt((int) (w * 0.2f)) 
							+ (int) (w * 0.8f), 
						random.nextInt(h));
			}
			// ��������ַ��� -- ��ȡ����ַ���
			String validateString = getValidateString();
			// ��������ַ��� -- ����
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
			// ��������ַ��� -- ����
			g.drawString(validateString, x, y);
			// ----- ���ˣ�������� -----
			// -------------------------
			ByteArrayOutputStream out = new ByteArrayOutputStream(); // ��������������ڽ�ͼƬת��Ϊbyte[]
			ImageIO.write(image, "png", out); // ��ͼƬд���������
			byte[] buf = out.toByteArray(); // ͨ��������õ�byte[]
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
