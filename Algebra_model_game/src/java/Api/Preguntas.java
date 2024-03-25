package Api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Preguntas extends HttpServlet {

    private PrintWriter outter;
    private final SAXBuilder builder = new SAXBuilder();
    private File xmlFile = null;
    private String ruta = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ruta = request.getRealPath("/");
        String queryStr = request.getQueryString();
        xmlFile = new File(ruta + "preguntas.xml");
        outter = response.getWriter();
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            Document document = (Document) builder.build(xmlFile); // Leemos el archivo
            Element rootNode = document.getRootElement(); // Obtener nodo raiz
            List list = rootNode.getChildren("PREGUNTA");
            StringBuilder json = new StringBuilder();
            json.append("[");
            for (Object node : list) {
                Element elemento = (Element) node;
                String id = elemento.getAttributeValue("ID");
                String nombre = elemento.getAttributeValue("NOMBRE");
                String tipo = elemento.getAttributeValue("TIPO");
                String width = elemento.getAttributeValue("WIDTH");
                String height = elemento.getAttributeValue("HEIGHT");
                
                String sizeh1 = elemento.getAttributeValue("SIZEH1");                
                String sizeh2_answer = elemento.getAttributeValue("SIZEH2_ANSWER");
                String sizew1 = elemento.getAttributeValue("SIZEW1");
                String sizew2_answer = elemento.getAttributeValue("SIZEW2_ANSWER");
                
                String square1 = elemento.getAttributeValue("SQUARE1");   
                String square2 = elemento.getAttributeValue("SQUARE2");
                String square3 = elemento.getAttributeValue("SQUARE3");
                String square4 = elemento.getAttributeValue("SQUARE4");
                String respuesta = elemento.getChildText("RESPUESTA");
                String expresion = elemento.getChild("RESPUESTA").getAttributeValue("EXPRETION");
//                List drags = elemento.getChild("DRAGS").getChildren("OPCION");
//                List targets = elemento.getChild("TARGETS").getChildren("OPCION");
                if (queryStr != null) {
                    //Obtener solo el nodo
                    String qId = queryStr.split("=")[1];
                    if (id.equals(qId)) {
                        json.append("{");
                        json.append(jsonValue("id", id)).append(",");
                        json.append(jsonValue("nombre", nombre)).append(",");
                        json.append(jsonValue("tipo", tipo)).append(",");
                        json.append(jsonValue("width", width)).append(",");
                        json.append(jsonValue("height", height)).append(",");

                        json.append(jsonValue("sizeh1", sizeh1)).append(",");
                        json.append(jsonValue("sizeh2_answer", sizeh2_answer)).append(",");
                        json.append(jsonValue("sizew1", sizew1)).append(",");
                        json.append(jsonValue("sizew2_answer", sizew2_answer)).append(",");
                        json.append(jsonValue("sizew1", sizew1)).append(",");
                        
                        json.append(jsonValue("square1", square1)).append(",");
                        json.append(jsonValue("square2", square2)).append(",");
                        json.append(jsonValue("square3", square3)).append(",");
                        json.append(jsonValue("square4", square4)).append(",");
                        
                        json.append(jsonValue("respuesta", respuesta)).append(",");     
                        json.append(jsonValue("expresion", expresion));  
                        
//                        json.append(convertToJsonArray("drags", drags));
//                        json.append(",");
//                        json.append(convertToJsonArray("targets", targets));
                        json.append("}");
                    }
                } else {
                    json.append("{");
                    json.append(jsonValue("id", id)).append(",");
                    json.append(jsonValue("nombre", nombre)).append(",");
                    json.append(jsonValue("tipo", tipo)).append(",");
                    json.append(jsonValue("width", width)).append(",");
                    json.append(jsonValue("height", height)).append(",");          
                    
                    json.append(jsonValue("sizeh1", sizeh1)).append(",");
                    json.append(jsonValue("sizeh2_answer", sizeh2_answer)).append(",");
                    json.append(jsonValue("sizew1", sizew1)).append(",");
                    json.append(jsonValue("sizew2_answer", sizew2_answer)).append(",");     
                    
                    json.append(jsonValue("square1", square1)).append(",");
                    json.append(jsonValue("square2", square2)).append(",");
                    json.append(jsonValue("square3", square3)).append(",");
                    json.append(jsonValue("square4", square4)).append(",");

                    json.append(jsonValue("respuesta", respuesta)).append(",");     
                    json.append(jsonValue("expresion", expresion));                    
                    
//                    json.append(convertToJsonArray("drags", drags));
//                    json.append(",");
//                    json.append(convertToJsonArray("targets", targets));
                    json.append("}");
                    if (list.indexOf(node) != list.size() - 1) {
                        json.append(",");
                    }
                }

            }
            json.append("]");
            //System.out.println(json.toString());
            outter.write(json.toString());
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
    }

//   private String convertToJsonArray(String key, List list) {
//        StringBuilder jsonArray = new StringBuilder();
//        jsonArray.append("\"").append(key).append("\" : [");
//        int count = 1;
//        for (Object item : list) {
//            Element option = (Element) item;
//            String imagen = option.getAttributeValue("IMAGEN");
//            String value = option.getText();
//            jsonArray
//                    .append("{")
//                    .append(jsonValue("imagen", imagen))
//                    .append(",")
//                    .append(jsonValue("valor", value))
//                    .append("}");
//            if (count < list.size()) {
//                jsonArray.append(",");
//            }
//            count++;
//        }
//        jsonArray.append("]");
//        return jsonArray.toString();
//    }

    private String jsonValue(String key, Object value) {
        return new StringBuilder()
                .append("\"")
                .append(key)
                .append("\" : \"")
                .append(value)
                .append("\"")
                .toString();
    }

}
