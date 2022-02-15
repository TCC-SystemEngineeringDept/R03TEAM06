import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//���������X�g���珤�i���폜
@WebServlet("/listsakuzyo")

public class listsakuzyo extends HttpServlet {
	
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup(
					"java:/comp/env/jdbc/webapp");
			Connection con = ds.getConnection();

			String comm = request.getParameter("comm1");
			System.out.println("���i�ԍ�"+comm);
			PreparedStatement st = con.prepareStatement(
					"delete from comm_table where comm_id=?");
			
			st.setString(1, comm);
			
			st.executeUpdate();
			
			
			st.close();
			con.close();
			response.sendRedirect("http://localhost:8080/R03Team06/list");

			} catch (Exception e) {
				out.println("<pre>");
				e.printStackTrace(out);
			}
		
	}
	
}