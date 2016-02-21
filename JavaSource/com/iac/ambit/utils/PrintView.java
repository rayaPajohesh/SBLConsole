package com.iac.ambit.utils;

import org.apache.commons.lang.StringEscapeUtils;

import org.displaytag.export.BaseExportView;

import org.displaytag.model.TableModel;

import org.displaytag.properties.TableProperties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;


/**
 *
 * @author Hashir Ahmed
 */
public class PrintView extends BaseExportView
{
    
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 	
    public PrintView (  )
    {

    }

    protected String escapeColumnValue ( Object value )
    {
        if ( value != null )
        {
            return StringEscapeUtils.escapeHtml( value.toString(  ) );

        }

        return null;

    }

    protected boolean getAlwaysAppendCellEnd (  )
    {
        return true;

    }

    protected boolean getAlwaysAppendRowEnd (  )
    {
        return true;

    }

    protected String getCellEnd (  )
    {
        return "";

    }

    protected String getCellStart (  )
    {
        return "";

    }

    protected String getDocumentEnd (  )
    {
        return Config.getProperty( "documentEnd" );

    }

    protected String getDocumentStart (  )
    {
        return Config.getProperty( "documentStart" );

    }

    protected String getRowEnd (  )
    {
        return "";

    }

    protected String getRowStart (  )
    {
        return "";

    }

    public boolean outputPage (  )
    {
        return true;

    }

    /**
     * @see org.displaytag.export.ExportView#getMimeType()
     */
    public String getMimeType (  )
    {
        return "text/html";

    }

    public void setParameters ( 
        TableModel tableModel, boolean exportFullList, boolean includeHeader, boolean decorateValues )
    {
        // Force fully empty the table
        TableProperties tp = tableModel.getProperties(  );
        tp.clearProperties(  );
        tp.setUserProperties( new Properties(  ) );

        super.setParameters( 
            new TableModel( tp, "" ), exportFullList, includeHeader, decorateValues );

    }

}
