/*
 * Copyright (c) 2021 VMware, Inc.
 * SPDX-License-Identifier: MIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.vmware.ddlog.translator;

import java.util.HashSet;

/**
 * A very simple symbol table; currently maintains just names.
 */
public class SymbolTable {
    private final HashSet<String> names = new HashSet<String>();
    private int counter;

    public SymbolTable() {
        this.counter = 0;
    }

    public void addName(String name) {
        if (this.names.contains(name))
            throw new RuntimeException("Duplicate name: " + name);
        this.names.add(name);
    }

    public String freshName(String prefix) {
        String name = prefix;
        while (true) {
            if (this.names.contains(name)) {
                name = prefix + this.counter;
                this.counter++;
            } else {
                this.addName(name);
                return name;
            }
        }
    }
}
