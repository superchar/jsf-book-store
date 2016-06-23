package com.dataart.booksapp.ui.book.helpers;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by vlobyntsev on 15.06.2016.
 */
public class FileDownloadHelper {

    public static void downloadContentAsTextFile(String fileName,byte [] content) throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.responseReset();
        externalContext.setResponseContentType("text/plain");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".txt\"");
        try(OutputStream outputStream = externalContext.getResponseOutputStream()){
            outputStream.write(content);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

}
