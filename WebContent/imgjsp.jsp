<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--JSP--------------
<body>
	<div class="container">
		<div style="margin-top: 20vh; folat: left; heigh: 500px; width: 300px">
			<form class="form-group" action="chart" enctype="multipart/form-data"
				method="POST">
				<div class="uploadButton">上传头像<input type="file" name="file" /></div>
				<div class="img-display">
					<div class="preview"></div>
				</div>
				<input id="uploadTrigger" class="btn btn-success" type="submit"
					value="Upload">
			</form>
		</div>
		<img alt="img" style="width:100px;heigh:100px;" src="data:image/jpeg;base64,${imagesBytes}"/>
	</div>
</body>



----------------------preview.js-----------------------
$(function(){
	// 画像ファイルプレビュー表示のイベント追加 fileを選択時に発火するイベントを登録
	$('form').on('change', 'input[type="file"]', function(e) {
	    var file = e.target.files[0],
	        reader = new FileReader(),
	        $preview = $(".preview");
	        t = this;

	        console.log( e.target.files );
	    // 画像ファイル以外の場合は何もしない
	    if(file.type.indexOf("image") < 0){
	    	$("#uploadTrigger").prop("disabled", true);
	      return false;
	    }
	    // ファイル読み込みが完了した際のイベント登録
	    reader.onload = (function(file) {
	      return function(e) {
	        // 既存のプレビューを削除
	        $preview.empty();
	        // .prevewの領域の中にロードした画像を表示するimageタグを追加
	        $preview.append($('<img>').attr({
	                  src: e.target.result,
	                  //width: "150px",
	                  class: "preview",
	                  title: file.name
	              }));
	      };
	    })(file);
	    reader.readAsDataURL(file);

	    //preview画像の下にアップロードした画像ファイルの名前を取得して表示させる
	    var imgPath = $('input[type="file"]').prop('files')[0].name;
		$("#imageName").text( imgPath );
		$('input[type="hidden"]').val( imgPath );
		$("#uploadTrigger").prop("disabled", false);
	});
});
-----------------------------------DAO-------------------------------------
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBService {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URLSTR = "";
    private static final String USERNAME = "";
    private static final String USERPASSWORD ="";
    private Connection connnection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    static {
        try {
            // 加载数据库驱动程序
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        }
    }

    /**
     * 建立数据库连接
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            // 获取连接
            connnection = DriverManager.getConnection(URLSTR, USERNAME,
                    USERPASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connnection;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object[] params) {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            connnection = this.getConnection();
            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            affectedLine = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    public int insertcontent1(String sql,byte[] text) {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            connnection = this.getConnection();
            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if(text!=null) {
            	preparedStatement.setBytes(1, text);
            }

            // 执行
            affectedLine = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }



    public int insertcontent(String sql,String sender,String touser,byte[] text,String type) {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            connnection = this.getConnection();
            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if(sender!=null&&touser!=null&&text!=null&&type!=null) {
            	preparedStatement.setObject(1, sender);
            	preparedStatement.setObject(2,touser);
            	preparedStatement.setObject(3,text);
            	preparedStatement.setObject(4,type);
            }

            // 执行
            affectedLine = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }


    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public ResultSet executeQueryRS(String sql, Object[] params) {
        try {
            // 获得连接
            connnection = this.getConnection();

            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    /**
     * SQL 查询将查询结果：一行一列
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public Object executeQuerySingle(String sql, Object[] params) {
        Object object = null;
        try {
            // 获得连接
            connnection = this.getConnection();

            // 调用SQL
            preparedStatement = connnection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                object = resultSet.getObject(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql
     *            SQL语句
     * @return List
     *                       结果集
     */
    public List<Object> excuteQuery(String sql, Object[] params) {
        // 执行SQL获得结果集
        ResultSet rs = executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();

            // 获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        // 创建List
        List<Object> list = new ArrayList<Object>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭所有资源
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     * @param sql 存储过程语句
     * @param params 参数数组
     * @param outParamPos 输出参数位置
     * @param SqlType 输出参数类型
     * @return 输出参数的值
     */
    public Object excuteQuery(String sql, Object[] params,int outParamPos, int SqlType) {
        Object object = null;
        connnection = this.getConnection();
        try {
            // 调用存储过程
            callableStatement = connnection.prepareCall(sql);

            // 给参数赋值
            if(params != null) {
                for(int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }

            // 注册输出参数
            callableStatement.registerOutParameter(outParamPos, SqlType);

            // 执行
            callableStatement.execute();

            // 得到输出参数
            object = callableStatement.getObject(outParamPos);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 释放资源
            closeAll();
        }

        return object;
    }

    /**
     * 关闭所有资源
     */
    private void closeAll() {
        // 关闭结果集对象
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭PreparedStatement对象
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭Connection 对象
        if (connnection != null) {
            try {
                connnection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


---------------------------------------------------------------------------------------------
-->
</body>
</html>