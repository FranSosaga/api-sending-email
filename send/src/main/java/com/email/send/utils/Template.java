package com.email.send.utils;

public class Template {

    private String TEMPLATE_ORDER_DELAY = "<!DOCTYPE html>\n" +
            "<html lang=\"es\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <title>Detalle de la Orden</title>\n" +
            "  <style>\n" +
            "    body {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      margin: 0;\n" +
            "      padding: 20px;\n" +
            "      background-color: #f4f4f4;\n" +
            "    }\n" +
            "    .container {\n" +
            "      background-color: #fff;\n" +
            "      padding: 20px;\n" +
            "      border-radius: 8px;\n" +
            "      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n" +
            "      max-width: 600px;\n" +
            "      margin: 0 auto;\n" +
            "    }\n" +
            "    h2 {\n" +
            "      color: #333;\n" +
            "    }\n" +
            "    .order-details {\n" +
            "      margin-bottom: 20px;\n" +
            "    }\n" +
            "    .order-details table {\n" +
            "      width: 100%;\n" +
            "      border-collapse: collapse;\n" +
            "    }\n" +
            "    .order-details th, .order-details td {\n" +
            "      text-align: left;\n" +
            "      padding: 8px;\n" +
            "      border-bottom: 1px solid #ddd;\n" +
            "    }\n" +
            "    .order-details th {\n" +
            "      background-color: #f4f4f4;\n" +
            "      font-weight: bold;\n" +
            "    }\n" +
            "    .footer {\n" +
            "      margin-top: 20px;\n" +
            "      text-align: center;\n" +
            "      font-size: 12px;\n" +
            "      color: #777;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"container\">\n" +
            "    <h2>Resumen de la Orden</h2>\n" +
            "    \n" +
            "    <div class=\"order-details\">\n" +
            "      <table>\n" +
            "        <tr>\n" +
            "          <th>Orden</th>\n" +
            "          <td>{{order_id}}</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th>Fecha y hora de entrega (inicio)</th>\n" +
            "          <td>{{hora_entrega_inicio}}</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th>Fecha y hora de entrega (fin)</th>\n" +
            "          <td>{{hora_entrega_fin}}</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th>Fecha y hora real de entrega</th>\n" +
            "          <td>{{hora_real_entrega}}</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"footer\">\n" +
            "      <p>Detalle de la orden</p>\n" +
            "      <p>Equipo de marketing</p>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>\n";

    public String getTemplateOrderDelay(String orderId, String starDate, String finishDate, String dateDelivered){
        return TEMPLATE_ORDER_DELAY
                .replaceFirst("\\{\\{order_id}}", orderId)
                .replaceFirst("\\{\\{hora_entrega_inicio}}", starDate)
                .replaceFirst("\\{\\{hora_entrega_fin}}", finishDate)
                .replaceFirst("\\{\\{hora_real_entrega}}", dateDelivered);
    }

    public Template() {
    }
}
