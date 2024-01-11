package com.hoby.config;

import com.hoby.entity.User;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * PropertyEditor是JDK提供的类型转化工具
 *
 * @author hoby
 * @since 2023-12-25
 */
public class StringToUserPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = new User();
		user.setUsername(text);
		user.setPassword("jdk666");
		this.setValue(user);
	}
}
