/*
// $Id$
// LucidDB is a DBMS optimized for business intelligence.
// Copyright (C) 2006-2006 LucidEra, Inc.
// Copyright (C) 2006-2006 The Eigenbase Project
//
// This program is free software; you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by the Free
// Software Foundation; either version 2 of the License, or (at your option)
// any later version approved by The Eigenbase Project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package com.lucidera.luciddb.applib;

import java.sql.Types;

/**
 * strReplace replaces all occurences of the old string with the new string
 *
 * Ported from //bb/bb713/server/SQL/strReplace.java
 */
public class strReplace
{
    public static String FunctionExecute( String in, String oldStr, String newStr )
    {
        if( ( in == null ) || ( oldStr == null ) )
            return in;

        int prevPos = 0;
        int currPos = 0;
        int incrLen = oldStr.length();

        if (incrLen == 0) return in;

        StringBuffer retVal = new StringBuffer();

        try
        {
            while( (currPos = in.indexOf( oldStr, prevPos ) ) != -1 )
            {
                retVal.append( in.substring( prevPos, currPos ) );
                retVal.append( newStr );
                prevPos = currPos + incrLen;
            }
            retVal.append( in.substring( prevPos ) );
        }
        catch( StringIndexOutOfBoundsException e )
        {
        }

        return retVal.toString();
    }
}

// End strReplace.java