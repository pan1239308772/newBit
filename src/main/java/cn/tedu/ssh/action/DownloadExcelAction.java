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
			// arg0�����ݵļ���
			List<?> data = getUserList();
			// arg1�������е����ݵ�����
			Class<?> clazz = User.class;
			
			// ����������
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ����������
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			// ������1��
			HSSFRow row = sheet.createRow(0);
			
			// ��ȡ���ݵ��ֶ��б�
			Field[] fields = clazz.getDeclaredFields();
			
			// ����?����Ԫ��
			for (int i = 0; i < fields.length; i++) {
				// ���õ�ǰ�ֶοɼ�
				fields[i].setAccessible(true);
				// ������Ԫ��
				HSSFCell cell = row.createCell((short) i);
				// ��Ƹ���Ԫ����������ͣ��Ǳ�Ҫ��
				if ("java.lang.Integer"
						.equals(fields[i].getType().getName())) {
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				} else {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
				// �������
				cell.setCellValue(fields[i].getName());
			}
	
			// ѭ���������
			for (int i = 0; i < data.size(); i++) {
				// ������?��
				row = sheet.createRow(i + 1);
				// ѭ������?����Ԫ��
				for (int c = 0; c < fields.length; c++) {
					// ������Ԫ��
					HSSFCell cell = row.createCell((short) c);
					// ��Ƹ���Ԫ����������ͣ��Ǳ�Ҫ��
					if ("java.lang.Integer"
							.equals(fields[c].getType().getName())) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					}
					// �������
					cell.setCellValue(fields[c].get(data.get(i)).toString());
				}
			}
	
			// ������д�������������
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			// ͨ��������õ����ݵ�byte[]
			byte[] bytes = out.toByteArray();
			// �ر���
			out.close(); // ��������Դ����Ҫclose()
			// ����byte[]���������������Է��ظ��ͻ���
			setExcelStream(new ByteArrayInputStream(bytes));
	
			// ����
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
