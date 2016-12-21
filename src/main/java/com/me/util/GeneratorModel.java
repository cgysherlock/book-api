package com.me.util;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

public class GeneratorModel {
	private String packageOutPath = "com.yhq.model";// 指定实体生成所在包的路径
	private String authorName = "yhq";// 作者名字
	private String tablename = "ssf_concern";// 表名
	private String modelName = "Concern";
	private String pkgName = "com.yhq.model";// 包名
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接
	private static final String URL = "jdbc:mysql://localhost:3306/book";
	private static final String NAME = "root";
	private static final String PASS = "728198454";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/*
	 * 构造函数
	 */
	public GeneratorModel() {
		// 创建连接
		Connection con;
		// 查要生成实体类的表
		String sql = "select * from " + tablename + " where 1=2";
		PreparedStatement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			String content = parse(colnames, colTypes, colSizes);
			//String hbm = parseHbm(colnames, colTypes, colSizes);

			try {
				File directory = new File("");
				
				// System.out.println("绝对路径："+directory.getAbsolutePath());
				// System.out.println("相对路径："+directory.getCanonicalPath());
				String path = this.getClass().getResource("").getPath();
				String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/").replace("util", "model") + "/"
						+ initcap(modelName) + ".java";
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
				
				
//				File hbmDirectory = new File("");
//				String hbmOutputPath = hbmDirectory.getAbsolutePath() + "/src/main/resources/hbm/"+ initcap(modelName) + ".hbm.xml";
//				FileWriter hbmFw = new FileWriter(hbmOutputPath);
//				PrintWriter hbmPw = new PrintWriter(hbmFw);
//				hbmPw.println(hbm);
//				hbmPw.flush();
//				hbmPw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// try {
			// con.close();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("package " + this.packageOutPath + ";\r\n");
		sb.append("\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		sb.append("\r\n");
		sb.append("import javax.persistence.*;\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + modelName + " 实体类\r\n");
		sb.append(" * " + new Date() + " " + this.authorName + "\r\n");
		sb.append(" */ \r\n");
		// 实体部分
		sb.append("@Entity\r\n");
		sb.append("@Table(name = \"" + tablename + "\")\r\n");  
		sb.append("public class " + initcap(modelName) + "{\r\n");
		sb.append("\r\n");
		processAllAttrs(sb);// 属性
		processAllMethod(sb);// get set方法
		sb.append("}\r\n");

		// System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String parseHbm(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		
		
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<!DOCTYPE hibernate-mapping PUBLIC\r\n");
		sb.append("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\r\n");
		sb.append("    \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\r\n");
		sb.append("<hibernate-mapping package=\""+pkgName+"\">\r\n");
		sb.append("    <class name=\""+initcap(modelName)+"\" table=\"" + tablename + "\">\r\n");
		sb.append(" \r\n");
		sb.append("        <id name=\"id\">\r\n");
		sb.append("            <generator class=\"identity\" />\r\n");
		sb.append("        </id>\r\n");
		
		for (int i = 0 ; i < colnames.length ; i++) {
			sb.append("        <property name=\"" + parseString(colnames[i]) + "\" column=\"" + colnames[i] + "\" type=\""+sqlType2JavaType(colTypes[i])+"\" />\r\n");
		}
		sb.append("    </class>\r\n");
		sb.append(" \r\n");
		sb.append("</hibernate-mapping>\r\n");
		
		return sb.toString();
	}
	
	/**
	 * 驼峰法命名
	 * @param str
	 * @return
	 */
	private String parseString(String str) {
		int index = str.indexOf("_");
		if (index > 0) {
			String next = String.valueOf(str.charAt(index+1));
			String targrt = "_" + next; 
			String Upper = next.toUpperCase();
			return parseString(str.replace(targrt, Upper));
		}
		return str;
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			if (parseString(colnames[i]).equals("id")) {
				sb.append("\t@Id\r\n");
				sb.append("\t@GeneratedValue(strategy = GenerationType.AUTO)\r\n");
			} else {
				if (colnames[i].equals(parseString(colnames[i]))) {
					sb.append("\t@Column\r\n");
				} else {
					sb.append("\t@Column(name = \"" + colnames[i] + "\")\r\n");
				}
			}
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + parseString(colnames[i]) + ";\r\n");
			sb.append("\r\n");
		}

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + parseString(initcap(colnames[i])) + "(" + sqlType2JavaType(colTypes[i]) + " "
					+ parseString(colnames[i]) + "){\r\n");
			sb.append("\t\tthis." + parseString(colnames[i]) + " = " + parseString(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + parseString(initcap(colnames[i])) + "(){\r\n");
			sb.append("\t\treturn " + parseString(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integet";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}

	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new GeneratorModel();

	}

}