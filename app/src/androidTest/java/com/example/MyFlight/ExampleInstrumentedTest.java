package com.example.MyFlight;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import static org.junit.Assert.*;


public class ExampleInstrumentedTest {
    @Test
    public int test() {
        // Context of the app under test.
        Unit objs = new Android_Unit_Element(this, new Unit(), false);
        objs.getContent(Unit.setContentNull(), objs.toString());
    }
}