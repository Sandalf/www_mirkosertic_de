/*
 * Copyright 2017 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.classlib.java.lang.invoke;

import de.mirkosertic.bytecoder.api.SubstitutesInClass;
import de.mirkosertic.bytecoder.classlib.VM;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@SubstitutesInClass(completeReplace = true)
public class TLambdaMetafactory {

    public static CallSite metafactory(final MethodHandles.Lookup aCaller,
                                       final String aName,
                                       final MethodType aInvokedType,
                                       final MethodType aSamMethodType,
                                       final MethodHandle aImplMethod,
                                       final MethodType aInstantiatedMethodType) throws Throwable {

        return new VM.ImplementingCallsite(null) {
            @Override
            public Object invokeExact(final Object... args) throws Throwable {
                return VM.newRuntimeGeneratedType(aInvokedType, aImplMethod, args);
            }
        };
    }
}