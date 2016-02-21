package com.iac.ambit.utils;

import org.apache.struts.action.DynaActionForm;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Description Utility Class for escaping the Delimiter & single qoute characters
 * @author Hashir Ahmed
 */
public class EscapeInputUtility
{
    private static String delimiter = Config.getProperty( "Delimiter" );
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
    
	private static String[] arr = { "&lt;", "&gt;", "--", ";", ">", "<", "'",
		"!", "&", "#", "&quot;", "~",  "union", "nchar", "varchar",
		"nvarchar", "alter", "delete", "drop", "exec", "execute", "fetch",
		"insert", "select",  "sysobjects", "syscolumns", "update" };
	
	private EscapeInputUtility (  )
    {

    }
	public static boolean isValideInput(String input) {
		boolean result = true;
		
		Pattern pattern;
		Matcher matcher;
		String patternValue = "";
		for (int i = 0; i < arr.length; i++) {
			patternValue = arr[i];
			pattern = Pattern.compile(patternValue);
			matcher = pattern.matcher(input.toLowerCase());

			if (matcher.find())
				result = false;
		}
		return result;
	}
	
	public static String escapeInput(String input) {
		int i = 0;

		try {
			if ((input != null) && (input.length() > 0)) {

				// remove /, /0 for date and "@" for email

				for (i = 0; i < arr.length; i++) {

					if (input.toLowerCase().contains((String) arr[i])) {

						String oldString = (String) arr[i];

						input = input.toLowerCase().replaceAll(oldString, "");

					}
				}

				input = input.replaceAll("\\[", "");
				input = input.replaceAll("\\]", "");
				input = input.replaceAll("\\*", "");
				input = input.replaceAll("\\(", "");
				input = input.replaceAll("\\)", "");
				input = input.replaceAll("\\+", "");
				input = input.replaceAll("\\%00", "");
				input = input.replaceAll("\\\\", "");
				String delimiterEscapedInput = input.replaceAll("\\"
						+ delimiter, "");
				return delimiterEscapedInput.replaceAll("'", "").trim();

			}
		} catch (Exception e) {
			System.out.println("************************ : input : " + input);
			System.out.println("************************ : arr : " + arr[i]);
		}
		return input;

	}

    /**
     * 
     * Method for escaping all paramters in a Dyna Form bean
     * @param form
     */
    public static void escapeInput ( DynaActionForm form )
    {
        escapeInput( form.getMap(  ) );

    }

    private static void escapeInput ( Map map )
    {
        Set keys = map.keySet(  );
        Collection values = map.values(  );

        if ( !map.isEmpty(  ) )
        {
            Object[] keyArray = keys.toArray(  );
            Object[] valueArray = values.toArray(  );

            int i = 0;

            while ( i < keyArray.length )
            {
                Object k = keyArray[i];
                Object v = valueArray[i];
                i++;

                if ( v instanceof String )
                {
                    String escapedValue = escapeInput( (String) v );

                    if ( !v.equals( escapedValue ) )
                    {
                        map.remove( k );
                        map.put( k, escapedValue );

                    }

                }

            }

        }

    }

}
