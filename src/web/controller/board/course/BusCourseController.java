package web.controller.board.course;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;


@WebServlet("/BusCourseController.json")
public class BusCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		StringBuilder res = new StringBuilder("");
		
		double lat = Double.parseDouble(request.getParameter("gpsLati"));
		double lon = Double.parseDouble(request.getParameter("gpsLong"));
		
        StringBuilder urlBuilder = new StringBuilder("http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=QRPnr03SiRQlqYZBSknw2C0nfsjwPUcF8tioxSqkiVrT%2FfYMkEsbF1z7535b9ajjolQ%2B6wWtNsocpj29h5bWDA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("gpsLati","UTF-8") + "=" + URLEncoder.encode(lat+"", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("gpsLong","UTF-8") + "=" + URLEncoder.encode(lon+"", "UTF-8"));
       
        try {
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc  = dBuilder.parse(urlBuilder.toString());
			
			doc.getDocumentElement().normalize();
			System.out.println("Root elelment : " + doc.getDocumentElement().getNodeName());
			
			//파싱할 태그
			NodeList nList = doc.getElementsByTagName("item");
			
			for(int temp = 0 ; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					if(temp==0) {
						res.append("[");
					}
					Element element = (Element) nNode;
					System.out.println("버스 정류장 명칭 :  " + getTagValue("nodenm", element));
					System.out.println("버스 정류장 번호 :  " + getTagValue("nodeno", element));
					
					res.append("{\"nodename\":\""+getTagValue("nodenm", element)+"\",\"nodeno\":\""+getTagValue("nodeno", element)+"\"}");
					if(!(temp==nList.getLength()-1)) {
						res.append(",");
					}else {
						res.append("]");
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
        String gson = new Gson().toJson(res.toString());
        
        System.out.println(res.toString());
        
        response.getWriter().write(gson);
        
        
        
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private String getTagValue(String tag, Element element) {
		NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if(nValue==null) {
			return null;
		}
		return nValue.getNodeValue();
				
	}

}
