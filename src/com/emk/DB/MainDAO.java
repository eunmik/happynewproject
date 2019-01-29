package com.emk.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;

import org.json.simple.JSONObject;

public class MainDAO {
	public JSONObject getData(String colorName) throws SQLException, NamingException {
		
		DBUtil db = new DBUtil();
		String query = "select distinct a.color as color, a.hex as hex, a.rgb as rgb FROM hex_rgb_table a, (select * from color_table where color1 = ? or color2 = ? ) b where a.color = b.color1 or a.color = b.color2";
		PreparedStatement pstmt = db.createPstmt(query);
		pstmt.setString(1, colorName);
		pstmt.setString(2, colorName);
		
		

		ResultSet rs = pstmt.executeQuery();
		ArrayList colorList = new ArrayList();
		ArrayList rgbList = new ArrayList();
		ArrayList hexList = new ArrayList(); 
		
		while(rs.next()) {
			colorList.add(rs.getString("color"));
			rgbList.add(rs.getString("rgb"));
			hexList.add(rs.getString("hex"));
			
			
		}
		JSONObject json = new JSONObject();
		json.put("color", colorList);
		json.put("rgb", rgbList);
		json.put("hex", hexList);
		
		return json;
		
		
	}
	
	
}
