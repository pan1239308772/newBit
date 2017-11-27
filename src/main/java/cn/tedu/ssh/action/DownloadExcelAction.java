package cn.tedu.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DownloadExcelAction {
	private InputStream excelStream;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String execute() {
		try {
			// arg0：数据的集合
			List<?> data = getUserList();
			// arg1：集合中的数据的类型
			Class<?> clazz = User.class;
			
			// 创建工作本
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 创建工作表
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			// 创建第1行
			HSSFRow row = sheet.createRow(0);
			
			// 获取数据的字段列表
			Field[] fields = clazz.getDeclaredFields();
			
			// 创建?个单元格
			for (int i = 0; i < fields.length; i++) {
				// 设置当前字段可见
				fields[i].setAccessible(true);
				// 创建单元格
				HSSFCell cell = row.createCell((short) i);
				// 设计各单元格的数据类型（非必要）
				if ("java.lang.Integer"
						.equals(fields[i].getType().getName())) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				} else {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
				// 添加数据
				cell.setCellValue(fields[i].getName());
			}
	
			// 循环添加数据
			for (int i = 0; i < data.size(); i++) {
				// 创建第?行
				row = sheet.createRow(i + 1);
				// 循环创建?个单元格
				for (int c = 0; c < fields.length; c++) {
					// 创建单元格
					HSSFCell cell = row.createCell((short) c);
					// 设计各单元格的数据类型（非必要）
					if ("java.lang.Integer"
							.equals(fields[c].getType().getName())) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					// 添加数据
					cell.setCellValue(fields[c].get(data.get(i)).toString());
				}
			}
	
			// 将数据写到输出流对象中
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			// 通过输出流得到数据的byte[]
			byte[] bytes = out.toByteArray();
			// 关闭流
			out.close(); // 连接型资源都需要close()
			// 基于byte[]创建输入流对象，以返回给客户端
			setExcelStream(new ByteArrayInputStream(bytes));
	
			// 返回
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "error";
	}
	
	private List<User> getUserList() {
		List<User> user = new ArrayList<User>();
		
		user.add(new User(1, "alex", "alex111", "13800138001", "alex@tedu.cn"));
		user.add(new User(2, "billy", "billy111", "13800138002", "billy@tedu.cn"));
		user.add(new User(3, "charley", "charley111", "13800138003", "charley@tedu.cn"));
		user.add(new User(4, "david", "david111", "13800138004", "david@tedu.cn"));
		user.add(new User(5, "edison", "esison111", "13800138005", "edison@tedu.cn"));
		
		return user;
	}

}
