package com.weexbox.core.widget;

import java.lang.System;

/**
 * Author:leon.wen
 * Time:2018/12/12   14:13
 * Description:This is FloatingDraftButton
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010\u0014\u001a\u00020\nJ\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00010\rj\b\u0012\u0004\u0012\u00020\u0001`\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0001J(\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\nH\u0002R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/weexbox/core/widget/FloatingDraftButton;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "floatingActionButtons", "Ljava/util/ArrayList;", "lastX", "lastY", "originX", "originY", "screenHeight", "screenWidth", "getButtonSize", "getButtons", "Lkotlin/collections/ArrayList;", "isDraftable", "", "onTouch", "v", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "registerButton", "", "floatingActionButton", "slideButton", "l", "t", "r", "b", "core_debug"})
public final class FloatingDraftButton extends com.google.android.material.floatingactionbutton.FloatingActionButton implements android.view.View.OnTouchListener {
    private int lastX;
    private int lastY;
    private int originX;
    private int originY;
    private final int screenWidth = 0;
    private final int screenHeight = 0;
    private java.util.ArrayList<com.google.android.material.floatingactionbutton.FloatingActionButton> floatingActionButtons;
    private java.util.HashMap _$_findViewCache;
    
    public final void registerButton(@org.jetbrains.annotations.NotNull()
    com.google.android.material.floatingactionbutton.FloatingActionButton floatingActionButton) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.google.android.material.floatingactionbutton.FloatingActionButton> getButtons() {
        return null;
    }
    
    public final int getButtonSize() {
        return 0;
    }
    
    public final boolean isDraftable() {
        return false;
    }
    
    private final void slideButton(int l, int t, int r, int b) {
    }
    
    @java.lang.Override()
    public boolean onTouch(@org.jetbrains.annotations.NotNull()
    android.view.View v, @org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    public FloatingDraftButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public FloatingDraftButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public FloatingDraftButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
}