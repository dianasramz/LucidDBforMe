/*
// $Id$
// Package org.eigenbase is a class library of data management components.
// Copyright (C) 2005-2005 The Eigenbase Project
// Copyright (C) 2005-2005 Disruptive Tech
// Copyright (C) 2005-2005 LucidEra, Inc.
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
package org.eigenbase.rex;

import org.eigenbase.util.*;
import org.eigenbase.sql.*;
import org.eigenbase.reltype.*;

/**
 * <code>RexCallBinding</code> implements {@link SqlOperatorBinding}
 * by referring to an underlying collection of {@link RexNode} operands.
 *
 * @author Wael Chatila
 * @version $Id$
 */
public class RexCallBinding extends SqlOperatorBinding
{
    private final RexNode[] operands;

    public RexCallBinding(
        RelDataTypeFactory typeFactory,
        SqlOperator sqlOperator,
        RexNode[] operands)
    {
        super(typeFactory, sqlOperator);
        this.operands = operands;
    }

    // implement SqlOperatorBinding
    public String getStringLiteralOperand(int ordinal)
    {
        return RexLiteral.stringValue(operands[ordinal]);
    }

    // implement SqlOperatorBinding
    public int getIntLiteralOperand(int ordinal)
    {
        //todo move this to RexUtil
        RexNode node = operands[ordinal];
        if (node instanceof RexLiteral) {
            return RexLiteral.intValue(node);
        } else if (node instanceof RexCall) {
            RexCall call = (RexCall) node;
            if (call.isA(RexKind.MinusPrefix)) {
                RexNode child = call.operands[0];
                if (child instanceof RexLiteral) {
                    return -RexLiteral.intValue(child);
                }
            }
        }
        throw Util.newInternal("should never come here");
    }

    // implement SqlOperatorBinding
    public boolean isOperandNull(int ordinal)
    {
        return RexUtil.isNull(operands[ordinal]);
    }

    // implement SqlOperatorBinding
    public int getOperandCount()
    {
        return operands.length;
    }

    // implement SqlOperatorBinding
    public RelDataType getOperandType(int ordinal)
    {
        return operands[ordinal].getType();
    }
}

// End RexCallBinding.java