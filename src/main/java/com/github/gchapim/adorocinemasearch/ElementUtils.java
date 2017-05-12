package com.github.gchapim.adorocinemasearch;

import org.jsoup.nodes.Element;

import com.github.gchapim.adorocinemasearch.util.Util;

/**
 * Util class for html elements handling purposes
 * @author gchapim
 *
 */
public class ElementUtils {

	public static Element findFirstTextString(Element parent, String query, boolean returnParent) {
		for(Element children : parent.children()){
			if(children.text().contains(query)){
				if(returnParent){
					return parent;
				}else{
					return children;
				}
			}else{
				Element newElement = findFirstTextString(children, query, returnParent);
				if(Util.filled(newElement)){
					return newElement;
				}
			}
		}
		 return null;
	}

}
