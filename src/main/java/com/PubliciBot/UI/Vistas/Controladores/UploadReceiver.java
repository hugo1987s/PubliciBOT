package com.PubliciBot.UI.Vistas.Controladores;

/**
 * Created by Hugo on 20/06/2017.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Image;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;

public class UploadReceiver implements Receiver , Upload.SucceededListener {
    private static final long serialVersionUID = 2215337036540966711L;

    // Find the application directory
    String basepath = VaadinService.getCurrent()
            .getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/";

    final Image image = new Image("Imagen subida");
    private File file;
    // Image as a file resource
    //FileResource resource = new FileResource(new File(basepath +
          //  "/WEB-INF/images/image.png"));

    OutputStream outputFile = null;
    @Override
    public OutputStream receiveUpload(String strFilename, String strMIMEType) {
        File file=null;
        try {
            file = new File(basepath + strFilename);
            if(!file.exists()) {
                file.createNewFile();
            }
            outputFile =  new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }

    protected void finalize() {
        try {
            super.finalize();
            if(outputFile!=null) {
                outputFile.close();
            }
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        image.setSource(new FileResource(file));
    }
}
