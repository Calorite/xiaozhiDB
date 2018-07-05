package Server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.codec.binary.Base64;

import DAO.DBupdate;
import DAO.Parama;

/**
 * Servlet implementation class HandleServlet
 */
@MultipartConfig(maxFileSize = 1048576) // Н≈Се1M
@WebServlet("/chart")
public class HandleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String path="C:\\Users\\works.DESKTOP-H20QRUB\\Bootcamp\\JavaSpace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\work\\Catalina\\localhost\\xiaozhiDB\\";
	private String getFileName(Part part) {
		String result = null;
		for(String disp : part.getHeader("Content-Disposition").split(";")) {
			disp = disp.trim();
			if (disp.startsWith("filename")) {
				result = disp.substring(disp.indexOf("=") + 1).trim();
				result = result.replace("\"", "").replace("\\","/");
				int pos =result.lastIndexOf("/");
				if (pos >= 0) {
					result = result.substring(pos + 1);
				}
				break;
			}
		}
		return result;
	}

	private boolean isValidFile(String name) {
		if (name != null) {
			String[] perms = { "gif", "jpg", "jpeg", "png" };
			String[] names = name.split("\\.");
			for (String perm: perms) {
				if (perm.equals(names[names.length - 1])) {
					return true;
				}
			}
		}
		return false;
	}


	private static String Picture2ByteStr(String imgpath) throws IOException{
        byte[] bytes  = Files.readAllBytes((new File(imgpath).toPath()));
        byte[] encodeBase64 = Base64.encodeBase64(bytes);
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//try {
			//parameterlist=DBupdate.getparams();
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("Imgupload.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part part = request.getPart("file");
		String name = this.getFileName(part);
		int id=1;
		if (this.isValidFile(name)) {
			//if(DBupdate.updateImg(id, path)) {
				//System.out.println("update succeed");
			//}
			part.write(name);
			String imgbyte=Picture2ByteStr(path+name);
			HttpSession session = request.getSession();
			session.setAttribute("imagesBytes", imgbyte);
			response.sendRedirect("Showuploaded.jsp");
		} else {
			response.getWriter().println("Invalid file is uploaded.");
		}
	}

}
