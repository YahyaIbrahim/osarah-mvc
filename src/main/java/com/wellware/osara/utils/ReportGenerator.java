package com.wellware.osara.utils;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;

@Component
public class ReportGenerator {


    private static IReportEngine birtEngine = null;

    private IReportRunnable design;

    private IRunAndRenderTask task = null;


    private void initializeBirtEngine() {
        if (birtEngine == null) {
            RegistryProviderFactory.releaseDefault();

            EngineConfig config = new EngineConfig();
            try {
                Platform.startup(config);
            } catch (BirtException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            IReportEngineFactory factory = (IReportEngineFactory) Platform
                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            birtEngine = factory.createReportEngine(config);
        }
    }


    public void generateReport(Map<String, Object> attributes, HttpServletRequest req, HttpServletResponse res) {
        initializeBirtEngine();
        try {
            HttpSession session = req.getSession();
            String reportName = attributes.get("report-name").toString();
            System.out.println("report Generator Entered");
            ServletContext sc = req.getSession().getServletContext();
            design = birtEngine.openReportDesign(getClass().getResourceAsStream("/reports/" + reportName + ".rptdesign"));
            //design = birtEngine.openReportDesign(sc.getRealPath("/BOOT-INF/classes/reports/") +"/"+ reportName + ".rptdesign");
            task = birtEngine.createRunAndRenderTask(design);
            populateReportData(attributes);
            populateReportType(req,res, reportName);
            task.getAppContext().put(EngineConstants.APPCONTEXT_BIRT_VIEWER_HTTPSERVET_REQUEST, req);
            task.run();
            task.close();
            Platform.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.shutdown();
        }

    }

    private void populateReportData(Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            if (entry.getKey().equals("report-name")) {
                continue;
            } else {
                task.setParameterValue(entry.getKey(), entry.getValue());
            }
        }
    }

    private void populateReportType(HttpServletRequest req,HttpServletResponse res, String reportName) throws IOException {

        HTMLRenderOption options = new HTMLRenderOption();
        options.setImageHandler(new HTMLServerImageHandler());
//            options.setImageDirectory(sc.getRealPath("/BOOT-INF/classes/reports/images"));
//            options.setBaseImageURL(req.getContextPath() + "/BOOT-INF/classes/reports/images");

        res.setCharacterEncoding("CP1256");
        options.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_PDF);
        res.setContentType("application/pdf");

        res.setHeader("Content-Disposition", "attachment; filename=" + reportName + ".pdf");

        res.setHeader("Content-Disposition", "inline; filename=" + reportName + ".pdf");

        res.setCharacterEncoding("CP1256");
        ServletOutputStream out = res.getOutputStream();
        options.setOutputStream(out);
        res.flushBuffer();

        task.setRenderOption(options);


    }

}
