package com.ideal.aem.platform.core.servlets;

import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.commons.osgi.PropertiesUtil;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class,
        immediate = true,
        property = {
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/ideal/weather",
                "sling.servlet.resourceTypes=" + "weretail/components/content/weather",
                "sling.servlet.selectors=bangalore",
                "sling.servlet.extensions=" + "json",
        })
/**
 * Handles requests for getting weather information from OpenWeatherMap.org.  returns the information as a JSon string.
 */
public class WeatherServlet extends SlingSafeMethodsServlet {

        private static final String SERVER = "localhost:4502";
        private static final String RESOURCE_PATH = "/content/OpenWeather";
        private String apikey = "";
        private String location = "";
        private ResourceResolver resourceResolver;

        private Logger logger = LoggerFactory.getLogger(WeatherServlet.class);

        @Override
        public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
                logger.info("Reconfigured Weather Servlet");
                getWeather(request, response);

        }

        /**
         * Gets current weather information from OpenWeatherMap.org API
         * @param request
         * @param response
         * @throws IOException
         */
        public void getWeather(SlingHttpServletRequest request, SlingHttpServletResponse response)   {
                logger.info("api key: " + apikey);
                logger.trace("api key: " + apikey);
                logger.debug("api key: " + apikey);
                location = request.getParameter("city");
                logger.info("city sent: " + location);
                String urlString = "http://api.openweathermap.org/data/2.5/weather?q=bangalore&units=imperial&APPID=d8e39388b0bc54a62ffc6b385639b3dc";
                logger.info("urlString: " + urlString);
                URL url = null;
                HttpURLConnection connection = null;
                int responseCode = -9;
                String result = "";
                logger.info("Before call to Open Weather");
                long startTime = System.currentTimeMillis();
                try {
                        url = new URL(urlString);
                        logger.info("url: " + url);
                        connection = (HttpURLConnection) url.openConnection();
                        logger.info("Connection: " + connection);
                        connection.setRequestMethod("GET");
                        responseCode = connection.getResponseCode();
                        logger.info("After calling Open Weather");
                        BufferedReader reader;
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        logger.info("reader: " + reader);
                        result = reader.readLine();
                        long stopTime = System.currentTimeMillis();
                        long elapsedTime = stopTime - startTime;
                        logger.info("Elapsed Time is... " + elapsedTime);
                        logger.info("result: " + result);
                        PrintWriter writer = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        writer.write(result);
                } catch (MalformedURLException e) {
                        logger.info("MalformedURL");
                        logger.error("api key: " + apikey);
                } catch (IOException e) {
                        logger.info("IOException!!!!!!!!");
                        logger.error("api key: " + apikey);
                        logger.info("Cause: " + e.getCause());
                }
        }


        protected void activate(ComponentContext context)
        {
                apikey = PropertiesUtil.toString(context.getProperties().get("apikey"), "d8e39388b0bc54a62ffc6b385639b3dc"); // Get the api key from the OSGi console
                System.out.println("weather servlet activated");
        }
}