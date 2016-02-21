/* JavaScript Virtual Keyboard (slim variant), version 2.6
 *
 * (C) 2006-2007 Dmitriy Khudorozhkov (mailto:kh_dmitry2001@mail.ru)
 *
 * This software is provided "as-is", without any express or implied warranty.
 * In no event will the author be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */
  var opened = false, vkb = null, text = null, insertionS = 0, insertionE = 0;

   var userstr = navigator.userAgent.toLowerCase();
   var safari = (userstr.indexOf('applewebkit') != -1);
   var gecko  = (userstr.indexOf('gecko') != -1) && !safari;
   var standr = gecko || window.opera || safari;

 function isValidNumber(fld,val)
       {   

   
            var strAllowed = '0123456789';
 			 keyPressed = val;
 			
 			 rv=valid(document.getElementById(fld.name),"^ *[0-9]+$");
 			 
    			if (!rv) {
      				fld.value='';
    			}
    			
              if ((strAllowed.indexOf(keyPressed) == -1 && !(val.charCodeAt(0)>=96 && val.charCodeAt(0)<=105) && val.charCodeAt(0) != 8 && val.charCodeAt(0) != 13)  ){
				fld.value='';
               } 
                     
          
        }

  function setCurrentField(fld)
{
	text = fld;
  	
}
 function FillValue(fld){
 
 	
	focusOnNextByVirtualKey(fld);

 	
	}
	 
  function focusOnNextByVirtualKey(obj){
  i = obj.id;
  len = obj.value.length;
  
		
		
	   if (i  == 'customerId' && len == 10){

	   	document.all.customerPassword.focus();
	    
		insertionS=0;
		insertionE=0;	    	
	     }
	    else if(i  == 'customerPassword' && len == 12){	
          		
	    	document.all.captcha.focus();
	    	insertionS=0;
			insertionE=0;
	    }
	  
	     else if(i  == 'captcha' && len == 6){	
          		
	    	document.all.customerId.focus();
	    	insertionS=0;
			insertionE=0;
	    }                		                	
  
   
         




}
  function keyb_change()
   {
     //document.getElementById("switch").innerHTML = (opened ? "<img border='none' src='locale/fa_IR/images/keyboard3.jpg' />" : "<img border='none' src='locale/fa_IR/images/keyboard3.jpg' />");
     opened = !opened;
 
     if(opened && !vkb)
     {
       // Note: all parameters, starting with 3rd, in the following
       // expression are optional; their values are equal to the
       // default parameter values for the VKeyboard object.
       // The only exception is 18th parameter (flash switch),
       // which is false by default.
	  
       vkb = new VKeyboard("keyboard",    // container's id
                           keyb_callback, // reference to the callback function
                           false,          // create the arrow keys or not? (this and the following params are optional)
                           false,          // create up and down arrow keys? 
                           false,         // reserved
                           false,          // create the numpad or not?
                           "Tahoma",            // font name ("" == system default)
                           "10px",        // font size in px
                           "#000",        // font color
                           "#F00",        // font color for the dead keys
                           "#FFF",        // keyboard base background color
                           "#FFF",        // keys' background color
                           "#DDD",        // background color of switched/selected item
                           "#777",        // border color
                           "#CCC",        // border/font color of "inactive" key (key with no value/disabled)
                           "#FFF",        // background color of "inactive" key (key with no value/disabled)
                           "#F77",        // border color of the language selector's cell
                           true,          // show key flash on click? (false by default)
                           "#CC3300",     // font color during flash
                           "#FF9966",     // key background color during flash
                           "#CC3300",     // key border color during flash
                           true,         // embed VKeyboard into the page?
                           true,          // use 1-pixel gap between the keys?
                           0);            // index(0-based) of the initial layout
     }
     else
     
       vkb.Show(opened);
       
		  text = document.getElementById("customerId");	  
		 

    
     text.focus();
	
  
   }
 
  
   function backFocus()
   {
  
     if(opened)
     {
       setRange(text, insertionS, insertionE);

       text.focus();
     }
   }

   // Advanced callback function:
   //
   function keyb_callback(ch)
   {
     var val = text.value;

     switch(ch)
     {
       case "BackSpace":
         if(val.length)
         {
           var span = null;

           if(document.selection)
             span = document.selection.createRange().duplicate();

           if(span && span.text.length > 0)
           {
             span.text = "";
             getCaretPositions(text);
           }
           else
             deleteAtCaret(text);
         }

         break;

       case "<":
         if(insertionS > 0)
           setRange(text, insertionS - 1, insertionE - 1);

         break;

       case ">":
         if(insertionE < val.length)
           setRange(text, insertionS + 1, insertionE + 1);

         break;

       case "/\\":
         if(!standr) break;

         var prev  = val.lastIndexOf("\n", insertionS) + 1;
         var pprev = val.lastIndexOf("\n", prev - 2);
         var next  = val.indexOf("\n", insertionS);

         if(next == -1) next = val.length;
         var nnext = next - insertionS;

         if(prev > next)
         {
           prev  = val.lastIndexOf("\n", insertionS - 1) + 1;
           pprev = val.lastIndexOf("\n", prev - 2);
         }

         // number of chars in current line to the left of the caret:
         var left = insertionS - prev;

         // length of the prev. line:
         var plen = prev - pprev - 1;

         // number of chars in the prev. line to the right of the caret:
         var right = (plen <= left) ? 1 : (plen - left);

         var change = left + right;
         setRange(text, insertionS - change, insertionE - change);

         break;

       case "\\/":
         if(!standr) break;

         var prev  = val.lastIndexOf("\n", insertionS) + 1;
         var next  = val.indexOf("\n", insertionS);
         var pnext = val.indexOf("\n", next + 1);

         if( next == -1)  next = val.length;
         if(pnext == -1) pnext = val.length;

         if(pnext < next) pnext = next;

         if(prev > next)
            prev  = val.lastIndexOf("\n", insertionS - 1) + 1;

         // number of chars in current line to the left of the caret:
         var left = insertionS - prev;

         // length of the next line:
         var nlen = pnext - next;

         // number of chars in the next line to the left of the caret:
         var right = (nlen <= left) ? 0 : (nlen - left - 1);

         var change = (next - insertionS) + nlen - right;
         setRange(text, insertionS + change, insertionE + change);

         break;

       default:
         insertAtCaret(text, (ch == "Enter" ? (window.opera ? '\r\n' : '\n') : ch));
     }
   }

   // This function retrieves the position (in chars, relative to
   // the start of the text) of the edit cursor (caret), or, if
   // text is selected in the TEXTAREA, the start and end positions
   // of the selection.
   //
   function getCaretPositions(ctrl)
   {
     var CaretPosS = -1, CaretPosE = 0;

     // Mozilla way:
     if(ctrl.selectionStart || (ctrl.selectionStart == '0'))
     {
       CaretPosS = ctrl.selectionStart;
       CaretPosE = ctrl.selectionEnd;

       insertionS = CaretPosS == -1 ? CaretPosE : CaretPosS;
       insertionE = CaretPosE;
     }
     // IE way:
     else if(document.selection && ctrl.createTextRange)
     {
       var start = end = 0;
       try
       {
         start = Math.abs(document.selection.createRange().moveStart("character", -10000000)); // start

         if (start > 0)
         {
           try
           {
             var endReal = Math.abs(ctrl.createTextRange().moveEnd("character", -10000000));

             var r = document.body.createTextRange();
             r.moveToElementText(ctrl);
             var sTest = Math.abs(r.moveStart("character", -10000000));
             var eTest = Math.abs(r.moveEnd("character", -10000000));

             if ((ctrl.tagName.toLowerCase() != 'input') && (eTest - endReal == sTest))
               start -= sTest;
           }
           catch(err) {}
         }
       }
       catch (e) {}

       try
       {
         end = Math.abs(document.selection.createRange().moveEnd("character", -10000000)); // end
         if(end > 0)
         {
           try
           {
             var endReal = Math.abs(ctrl.createTextRange().moveEnd("character", -10000000));

             var r = document.body.createTextRange();
             r.moveToElementText(ctrl);
             var sTest = Math.abs(r.moveStart("character", -10000000));
             var eTest = Math.abs(r.moveEnd("character", -10000000));

             if ((ctrl.tagName.toLowerCase() != 'input') && (eTest - endReal == sTest))
              end -= sTest;
           }
           catch(err) {}
         }
       }
       catch (e) {}

       insertionS = start;
       insertionE = end
     }
   }

   function setRange(ctrl, start, end)
   {
     if(ctrl.setSelectionRange) // Standard way (Mozilla, Opera, Safari ...)
     {
       ctrl.setSelectionRange(start, end);
     }
     else // MS IE
     {
       var range;

       try
       {
         range = ctrl.createTextRange();
       }
       catch(e)
       {
         try
         {
           range = document.body.createTextRange();
           range.moveToElementText(ctrl);
         }
         catch(e)
         {
           range = null;
         }
       }

       if(!range) return;

       range.collapse(true);
       range.moveStart("character", start);
       range.moveEnd("character", end - start);
       range.select();
     }

     insertionS = start;
     insertionE = end;
     FillValue(ctrl);
   }

   function deleteSelection(ctrl)
   {
     if(insertionS == insertionE) return;

     var tmp = (document.selection && !window.opera) ? ctrl.value.replace(/\r/g,"") : ctrl.value;
     ctrl.value = tmp.substring(0, insertionS) + tmp.substring(insertionE, tmp.length);

     setRange(ctrl, insertionS, insertionS);
   }

   function deleteAtCaret(ctrl)
   {
     // if(insertionE < insertionS) insertionE = insertionS;
     if(insertionS != insertionE)
     {
       deleteSelection(ctrl);
       return;
     }

     if(insertionS == insertionE)
       insertionS = insertionS - 1;

     var tmp = (document.selection && !window.opera) ? ctrl.value.replace(/\r/g,"") : ctrl.value;
     ctrl.value = tmp.substring(0, insertionS) + tmp.substring(insertionE, tmp.length);

     setRange(ctrl, insertionS, insertionS);
   }

   // This function inserts text at the caret position:
   //
   function insertAtCaret(ctrl, val)
   {
  		
     if(insertionS != insertionE) deleteSelection(ctrl);

     if(gecko && document.createEvent && !window.opera)
     {
       var e = document.createEvent("KeyboardEvent");

       if(e.initKeyEvent && ctrl.dispatchEvent)
       {
         e.initKeyEvent("keypress",        // in DOMString typeArg,
                        false,             // in boolean canBubbleArg,
                        true,              // in boolean cancelableArg,
                        null,              // in nsIDOMAbstractView viewArg, specifies UIEvent.view. This value may be null;
                        false,             // in boolean ctrlKeyArg,
                        false,             // in boolean altKeyArg,
                        false,             // in boolean shiftKeyArg,
                        false,             // in boolean metaKeyArg,
                        null,              // key code;
                        val.charCodeAt(0));// char code.

         ctrl.dispatchEvent(e);
        
         
       }
       
    //   if(ctrl.name == "customerId" ||ctrl.name == "customerPassword"  ){
       
    //   	isValidNumber(ctrl,val);
   //    }
       
       
     }
     else
     {
       var tmp = (document.selection && !window.opera) ? ctrl.value.replace(/\r/g,"") : ctrl.value;
       ctrl.value = tmp.substring(0, insertionS) + val + tmp.substring(insertionS, tmp.length);
     //  if(ctrl.name == "customerId" ||ctrl.name == "customerPassword"  ){
       
    //   	isValidNumber(ctrl,val);
     //  }
       
     }

     setRange(ctrl, insertionS + val.length, insertionS + val.length);
   }   



function VKeyboard(container_id, callback_ref, create_arrows, create_updown,
                   create_nav_keys, create_numpad, font_name, font_size,
                   font_color, dead_color, bg_color, key_color,
                   sel_item_color, border_color, inactive_border_color,
                   inactive_key_color, lang_sel_brd_color, show_click,
                   click_font_color, click_bg_color, click_border_color,
                   do_embed, do_gap, start_layout_index)
{
  return this._construct(container_id, callback_ref, create_arrows, create_updown,
                         create_nav_keys, create_numpad, font_name, font_size,
                         font_color, dead_color, bg_color, key_color,
                         sel_item_color, border_color, inactive_border_color,
                         inactive_key_color, lang_sel_brd_color, show_click,
                         click_font_color, click_bg_color, click_border_color,
                         do_embed, do_gap, start_layout_index);
}

VKeyboard.kbArray = [];

VKeyboard.prototype = {

  _get_event_source: function(event)
  {
    var e = event || window.event;
    return e.srcElement || e.target;
  },

  _setup_event: function(elem, eventType, handler)
  {
    return (elem.attachEvent ? elem.attachEvent("on" + eventType, handler) : ((elem.addEventListener) ? elem.addEventListener(eventType, handler, false) : null));
  },

  _detach_event: function(elem, eventType, handler)
  {
    return (elem.detachEvent ? elem.detachEvent("on" + eventType, handler) : ((elem.removeEventListener) ? elem.removeEventListener(eventType, handler, false) : null));
  },

  _start_flash: function(in_el)
  {
    function getColor(str, posOne, posTwo)
    {
      if(/rgb\((\d+),\s(\d+),\s(\d+)\)/.exec(str)) // try to detect Mozilla-style rgb value.
      {
        switch(posOne)
        {
          case 1: return parseInt(RegExp.$1, 10);
          case 2: return parseInt(RegExp.$2, 10);
          case 3: return parseInt(RegExp.$3, 10);
          default: return 0;
        }
      }
      else // standard (#xxxxxx or #xxx) way
        return str.length == 4 ? parseInt(str.substr(posOne, 1) + str.substr(posOne, 1), 16) : parseInt(str.substr(posTwo, 2), 16);
    }

    function getR(color_string)
    { return getColor(color_string, 1, 1); }

    function getG(color_string)
    { return getColor(color_string, 2, 3); }

    function getB(color_string)
    { return getColor(color_string, 3, 5); }

    var el = in_el.time ? in_el : (in_el.company && in_el.company.time ? in_el.company : null);

    if(el)
    {
      el.time = 0;
      clearInterval(el.timer);
    }

    var vkb = this;
    var ftc = vkb.fontcolor, bgc = vkb.keycolor, brc = vkb.bordercolor;

    // Special fixes for simple/dead/modifier keys:

    if(in_el.dead)
      ftc = vkb.deadcolor;

    if(((in_el.innerHTML == "Shift") && vkb.Shift) || ((in_el.innerHTML == "Caps") && vkb.Caps) || ((in_el.innerHTML == "AltGr") && vkb.AltGr))
      bgc = vkb.lic;

    // Extract base color values:
    var fr = getR(ftc), fg = getG(ftc), fb = getB(ftc);
    var kr = getR(bgc), kg = getG(bgc), kb = getB(bgc);
    var br = getR(brc), bg = getG(brc), bb = getB(brc);

    // Extract flash color values:
    var f_r = getR(vkb.cfc), f_g = getG(vkb.cfc), f_b = getB(vkb.cfc);
    var k_r = getR(vkb.cbg), k_g = getG(vkb.cbg), k_b = getB(vkb.cbg);
    var b_r = getR(vkb.cbr), b_g = getG(vkb.cbr), b_b = getB(vkb.cbr);

    var _shift_colors = function()
    {
      function dec2hex(dec)
      {
        var hexChars = "0123456789ABCDEF";
        var a = dec % 16;
        var b = (dec - a) / 16;

        return hexChars.charAt(b) + hexChars.charAt(a) + "";
      }

      in_el.time = !in_el.time ? 10 : (in_el.time - 1);

      function calc_color(start, end)
      { return (end - (in_el.time / 10) * (end - start)); }

      var t_f_r = calc_color(f_r, fr), t_f_g = calc_color(f_g, fg), t_f_b = calc_color(f_b, fb);
      var t_k_r = calc_color(k_r, kr), t_k_g = calc_color(k_g, kg), t_k_b = calc_color(k_b, kb);
      var t_b_r = calc_color(b_r, br), t_b_g = calc_color(b_g, bg), t_b_b = calc_color(b_b, bb);

      function setStyles(style)
      {
        style.color = "#" + dec2hex(t_f_r) + dec2hex(t_f_g) + dec2hex(t_f_b);
        style.borderColor = "#" + dec2hex(t_b_r) + dec2hex(t_b_g) + dec2hex(t_b_b);
        style.backgroundColor = "#" + dec2hex(t_k_r) + dec2hex(t_k_g) + dec2hex(t_k_b);
      }

      var first = (in_el == vkb.mod[4]) ? false : true, is = in_el.style, cs = in_el.company ? in_el.company.style : null;

      if(cs && first)
        setStyles(cs);

      setStyles(is);

      if(cs)
      {
        if(!first)
        {
          setStyles(cs);
          is.borderBottomColor = "#" + dec2hex(t_k_r) + dec2hex(t_k_g) + dec2hex(t_k_b);
        }
        else
          cs.borderBottomColor = "#" + dec2hex(t_k_r) + dec2hex(t_k_g) + dec2hex(t_k_b);
      }

      if(!in_el.time)
      {
        clearInterval(in_el.timer);
        return;
      }
    };

    _shift_colors();

    in_el.timer = window.setInterval(_shift_colors, 50);
  },

  _setup_style: function(obj, top, left, width, height, position, text_align, line_height, font_size, font_weight, padding_left, padding_right)
  {
    var os = obj.style;
	
    if(top)    os.top = top;
    if(left)   os.left = left;
    if(width)  os.width = width;
    if(height) os.height = height;

    if(position) os.position = position;

    if(text_align)  os.textAlign  = text_align;
    if(line_height) os.lineHeight = line_height;
    if(font_size)   os.fontSize   = font_size;

    os.fontWeight = font_weight || "bold";

    if(padding_left)  os.paddingLeft  = padding_left;
    if(padding_right) os.paddingRight = padding_right;
  },

  _setup_key: function(parent, id, top, left, width, height, text_align, line_height, font_size, font_weight, padding_left, padding_right)
  {
    var _id = this.Cntr.id + id;
    var exists = document.getElementById(_id);

    var key = exists ? exists.parentNode : document.createElement("DIV");
    this._setup_style(key, top, left, width, height, "absolute");

    var key_sub = exists || document.createElement("DIV");
    key.appendChild(key_sub); parent.appendChild(key);

    this._setup_style(key_sub, "", "", "", line_height, "relative", text_align, line_height, font_size, font_weight, padding_left, padding_right);
    key_sub.id = _id;

    return key_sub;
  },

  _findX: function(obj)
  { return (obj && obj.parentNode) ? parseFloat(obj.parentNode.offsetLeft) : 0; },

  _findY: function(obj)
  { return (obj && obj.parentNode) ? parseFloat(obj.parentNode.offsetTop) : 0; },

  _findW: function(obj)
  { return (obj && obj.parentNode) ? parseFloat(obj.parentNode.offsetWidth) : 0; },

  _findH: function(obj)
  { return (obj && obj.parentNode) ? parseFloat(obj.parentNode.offsetHeight) : 0; },

  _construct: function(container_id, callback_ref, create_arrows, create_updown, create_nav_keys, create_numpad,
                       font_name, font_size, font_color, dead_color, bg_color, key_color, sel_item_color,
                       border_color, inactive_border_color, inactive_key_color, lang_sel_brd_color,
                       show_click, click_font_color, click_bg_color, click_border_color, do_embed,
                       do_gap, start_layout_index)
  {
    var exists  = (this.Cntr != undefined), ct = exists ? this.Cntr : document.getElementById(container_id);
    var changed = (font_size && (font_size != this.fontsize));

    this._Callback = ((typeof(callback_ref) == "function") && ((callback_ref.length == 1) || (callback_ref.length == 2))) ? callback_ref : (this._Callback || null);

    var ff = font_name || this.fontname || "";
    var fs = font_size || this.fontsize || "14px";

    var fc = font_color   || this.fontcolor   || "#000";
    var dc = dead_color   || this.deadcolor   || "#F00";
    var bg = bg_color     || this.bgcolor     || "#FFF";
    var kc = key_color    || this.keycolor    || "#FFF";
    var bc = border_color || this.bordercolor || "#777";

    this.fontname = ff, this.fontsize = fs, this.fontcolor = fc;
    this.bgcolor = bg,  this.keycolor = kc, this.deadcolor = dc, this.bordercolor = bc;

    this.lic = sel_item_color        || this.lic || "#DDD";
    this.ibc = inactive_border_color || this.ibc || "#CCC";
    this.ikc = inactive_key_color    || this.ikc || "#FFF";
    this.lsc = lang_sel_brd_color    || this.lsc || "#F77";

    this.cfc = click_font_color   || this.cfc || "#CC3300";
    this.cbg = click_bg_color     || this.cbg || "#FF9966";
    this.cbr = click_border_color || this.cbr || "#CC3300";

    this.sc = (show_click == undefined) ? ((this.sc == undefined) ? false : this.sc) : show_click;
    this.gap = (do_gap != undefined) ? (do_gap ? 1 : -1) : (this.gap || 1);

    if(!exists)
    {
      this.Cntr = ct;
      this.Caps = this.Shift = this.AltGr = false;

      this.DeadAction = []; this.DeadAction[0] = this.DeadAction[1] = null;
      this.keys = [], this.mod = [], this.pad = [];

      VKeyboard.kbArray[container_id] = this;
    }

    var kb = exists ? ct.childNodes[0] : document.createElement("DIV");

    if(!exists)
    {
      ct.appendChild(kb);
      ct.style.display = "block";
      ct.style.zIndex  = 999;

      if(do_embed)
        ct.style.position = "relative";
      else
      {
        ct.style.position = "absolute";

        var initX = 0, initY = 0, ct_ = ct;
        if(ct_.offsetParent)
        {
          while (ct_.offsetParent)
          {
            initX += ct_.offsetLeft;
            initY += ct_.offsetTop;

            ct_ = ct_.offsetParent;
          }
        }
        else if (ct_.x)
        {
          initX += ct_.x;
          initY += ct_.y;
        }

        ct.style.top = initY + "px", ct.style.left = initX +"px";
      }

      kb.style.position = "relative";
      kb.style.top      = "0px", kb.style.left = "0px";
    }
	kb.dir="rtl";
    kb.style.border = "1px solid " + bc;
    
	
	
    var kb_main = exists ? kb.childNodes[0] : document.createElement("DIV"), ks = kb_main.style;
    if(!exists)
    {
      kb.appendChild(kb_main);

      ks.position = "relative";
      ks.width    = "1px";
      ks.cursor   = "default";
    }
   
	ks.width    = "287px";
    // Disable content selection:
    this._setup_event(kb_main, "selectstart", function(event) { return false; });
    this._setup_event(kb_main, "mousedown",   function(event) { if(event.preventDefault) event.preventDefault(); return false; });

    ks.fontFamily = ff, 
    //ks.backgroundColor = bg;
 	ks.backgroundcolor="silver";
    if(!exists || changed)
    {
      var mag = parseFloat(fs) / 14.0, cell = Math.floor(25.0 * mag), dcell = 2 * cell, gap = this.gap;
      var cp = String(cell) + "px", cx = String(cell - (do_gap ? 0 : 2.0)) + "px", lh = String(cell - 2.0) + "px";

      var prevX = 0, prevY = gap, prevW = 0, prevH = 0;

      // Convenience strings:
      var c = "center", n = "normal", r = "right", l = "left", e = "&nbsp;", pad = String(4 * mag) + "px";

      // Number row:

      var key;
      for(var i = 0; i < 13; i++)
      {      	
      	
        this.keys[i] = key = this._setup_key(kb_main, "___key" + String(i), prevY + "px", (prevX + prevW + gap) + "px", cp, cp, c, lh, fs);
        if( i==12 || i==11)this.keys[i].disabled=false;
        prevX = this._findX(key), prevW = this._findW(key);
      }

      prevY = this._findY(key);
      prevH = this._findH(key); // universal key height

      var kb_kbp = this._setup_key(kb_main, "___kbp", prevY + "px", (prevX + prevW + gap) + "px", (2.96 * cell) + "px", cp, r, lh, fs, n, "", pad);
      kb_kbp.innerHTML = "BackSpace";
      this.mod[0] = kb_kbp;

      // Top row:
				//   this._setup_key(kb_main, "___caps", (prevY + prevH + gap) + "px", gap + "px", dcell + "px", cp, l, lh, fs, n, pad);
      var kb_tab = this._setup_key(kb_main, "___tab", (prevY + prevH + gap) + "px", gap + "px", (1.48 * cell + gap) + "px", cp, l, lh, fs, n, pad);
      kb_tab.innerHTML = "Tab";
      this.mod[1] = kb_tab;

      prevX = this._findX(kb_tab), prevW = this._findW(kb_tab), prevY = this._findY(kb_tab);

      for(; i < 26; i++)
      {
        this.keys[i] = key = this._setup_key(kb_main, "___key" + String(i), prevY + "px", (prevX + prevW + gap) + "px", cp, cp, c, lh, fs);
		if(i==23 || i==24 || i==25)this.keys[i].disabled=false;
        prevX = this._findX(key), prevW = this._findW(key);
      }

      this.kbpH = this._findX(kb_kbp) + this._findW(kb_kbp);

      // Home row:

      var kb_caps = this._setup_key(kb_main, "___caps", (prevY + prevH + gap) + "px", gap + "px", dcell + "px", cp, l, lh, fs, n, pad);
      kb_caps.innerHTML = "Caps";
      this.mod[2] = kb_caps;

      prevX = this._findX(kb_caps), prevW = this._findW(kb_caps), prevY = this._findY(kb_caps);

      for(; i < 38; i++)
      {
        this.keys[i] = key = this._setup_key(kb_main, "___key" + String(i), prevY + "px", (prevX + prevW + gap) + "px", cp, cp, c, lh, fs);
		if(i==35 || i==36 || i==37)this.keys[i].disabled=false;
        prevX = this._findX(key), prevW = this._findW(key);
      }

      prevY = this._findY(key);
      var s = prevX + prevW + gap;

      var kb_enter = this._setup_key(kb_main, "___enter_l", prevY + "px", s + "px", (this.kbpH - s) + "px", cp, r, lh, fs, n, "", pad);
      kb_enter.innerHTML = "Enter";
      kb_enter.disabled = false;
      this.mod[3] = kb_enter;

      s = this._findX(this.keys[25]) + this._findW(this.keys[25]) + gap;

      var kb_enter_top = this._setup_key(kb_main, "___enter_top", this._findY(kb_tab) + "px", s + "px", (this.kbpH - s) + "px", cx, c, cx);
      kb_enter_top.innerHTML = e;
      kb_enter_top.disabled=false;
      kb_enter_top.subst = "Enter";
      this.mod[4] = kb_enter_top;

      kb_enter_top.company = kb_enter;
      kb_enter.company = kb_enter_top;

      // Bottom row:

      var kb_shift = this._setup_key(kb_main, "___shift", (prevY + prevH + gap) + "px", gap + "px", (2.52 * cell) + "px", cp, l, lh, fs, n, pad);
      kb_shift.innerHTML = "Shift";
      kb_shift.disabled=false;
      this.mod[5] = kb_shift;

      prevX = this._findX(kb_shift), prevW = this._findW(kb_shift), prevY = this._findY(kb_shift);

      for(; i < 48; i++)
      {
        this.keys[i] = key = this._setup_key(kb_main, "___key" + String(i), prevY + "px", (prevX + prevW + gap) + "px", cp, cp, c, lh, fs);
		if(i==45 ||  i==47)this.keys[i].disabled=false;
        prevX = this._findX(key), prevW = this._findW(key);
      }

      prevY = this._findY(key);

      var kb_shift_r = this._setup_key(kb_main, "___shift_r", prevY + "px", (prevX + prevW + gap) + "px", (this._findX(kb_kbp) + this._findW(kb_kbp) - prevX - prevW - gap) + "px", cp, r, lh, fs, n, "", pad);
      kb_shift_r.innerHTML = "Shift";
      kb_shift_r.disabled=false;
      this.mod[6] = kb_shift_r;

      // Language selector:

      var vcell = String(1.32 * cell) + "px";

      var kb_lang = this._setup_key(kb_main, "___lang", (prevY + prevH + gap) + "px", gap + "px", vcell, cp, l, lh, fs, n, pad);
      this.mod[7] = kb_lang;

      prevY = this._findY(kb_lang);

           
      ks.height = (prevY + prevH + gap) + "px";

      prevY += "px";

      var kb_res_1 = this._setup_key(kb_main, "___res_1", prevY, (this._findX(kb_lang) + this._findW(kb_lang) + gap) + "px", vcell, cp, c, lh, fs);
      kb_res_1.innerHTML = e;
      this.mod[8] = kb_res_1;

      var kb_res_2 = this._setup_key(kb_main, "___res_2", prevY, (this._findX(kb_res_1) + this._findW(kb_res_1) + gap) + "px", vcell, cp, c, lh, fs);
      kb_res_2.innerHTML = e;
      this.mod[9] = kb_res_2;
						
      var kb_space = this._setup_key(kb_main, "___space", prevY, (this._findX(kb_res_2) + this._findW(kb_res_2) + gap) + "px", (6.28 * cell) + "px", cp, c, lh, fs);
      kb_space.innerHTML = "";
      kb_space.disabled=false;
      this.mod[10] = kb_space;

      var kb_alt_gr = this._setup_key(kb_main, "___alt_gr", prevY, (this._findX(kb_space) + this._findW(kb_space) + gap) + "px", vcell, cp, c, lh, fs);
      kb_alt_gr.innerHTML = "Alt";
      kb_alt_gr.disabled=false;
      this.mod[11] = kb_alt_gr;

     var kb_res_3 = this._setup_key(kb_main, "___res_3", prevY, (this._findX(kb_alt_gr) + this._findW(kb_alt_gr) + gap) + "px", vcell, cp, c, lh, fs);
      kb_res_3.innerHTML = e;
      this.mod[12] = kb_res_3;

      var kb_res_4 = this._setup_key(kb_main, "___res_4", prevY, (this._findX(kb_alt_gr) + this._findW(kb_alt_gr) + gap) + "px", vcell, cp, c, lh, fs);
      kb_res_4.innerHTML = e;
      this.mod[13] = kb_res_4;
       
    

      var w = this.kbpH + gap;

      // Arrow keys:
      if(!exists && ((create_arrows == undefined) ? true : create_arrows))
      {
        var kb_left = this._setup_key(kb_main, "___left", prevY, (this.kbpH + gap + cell / 2) + "px", cp, cp, c, lh, fs);
        kb_left.innerHTML = "&lt;"
        this.mod[14] = kb_left;

        if((create_updown == undefined) ? true : create_updown)
        {
          var kb_down = this._setup_key(kb_main, "___down", prevY, (this._findX(kb_left) + this._findW(kb_left) + gap) + "px", cp, cp, c, lh, fs);
          kb_down.innerHTML = "\\/"
          this.mod[15] = key = kb_down;

          var kb_up = this._setup_key(kb_main, "___up", this._findY(kb_shift_r) + "px", (this._findX(kb_left) + this._findW(kb_left) + gap) + "px", cp, cp, c, lh, fs);
          kb_up.innerHTML = "/\\"
          this.mod[16] = kb_up;
        }
		else key = kb_left;

        var kb_right = this._setup_key(kb_main, "___right", prevY, (this._findX(key) + this._findW(key) + gap) + "px", cp, cp, c, lh, fs);
        kb_right.innerHTML = "&gt;"
        this.mod[this.mod.length] = kb_right;
      }

      this.kbpH = this._findX(kb_right) + this._findW(kb_right);
      w = this.kbpH + gap;

      // Numpad:

      if((create_numpad == undefined) ? true : create_numpad)
      {
        var w2 = this._create_numpad(container_id, kb_main);
 
        if(w2 > w) w = w2;
      }

     // kb.style.width = ks.width = w + "px";
      
     
    }

    this._refresh_layout(this.avail_langs[start_layout_index || 0][0]);

    return this;
  },

  _create_numpad: function(container_id, parent)
  {
    var c = "center", n = "normal", l = "left";
    var fs = this.fontsize, bc = this.bordercolor, gap = this.gap;

    var mag = parseFloat(fs) / 14.0, cell = Math.floor(25.0 * mag);
    var dcell = 2 * cell, dp = (dcell + 1) + "px", dp2 = (dcell - 1 - ((gap < 0) ? 2 : 0)) + "px";
    var cp = String(cell) + "px", lh = String(Math.floor(cell - 2.0)) + "px";

    var edge = (this.kbpH + cell / 2 + gap) + "px";

    var kb_pad_eur = this._setup_key(parent, "___pad_eur", gap + "px", edge, cp, cp, c, lh, fs);
    kb_pad_eur.innerHTML = "&#x20AC;";
    this.pad[0] = kb_pad_eur;

    var edge_1 = (this._findX(kb_pad_eur) + this._findW(kb_pad_eur) + gap) + "px";

    var kb_pad_slash = this._setup_key(parent, "___pad_slash", gap + "px", edge_1, cp, cp, c, lh, fs);
    kb_pad_slash.innerHTML = "/";
    this.pad[1] = kb_pad_slash;

    var edge_2 = (this._findX(kb_pad_slash) + this._findW(kb_pad_slash) + gap) + "px";

    var kb_pad_star = this._setup_key(parent, "___pad_star", gap + "px", edge_2, cp, cp, c, lh, fs);
    kb_pad_star.innerHTML = "*";
    this.pad[2] = kb_pad_star;

    var edge_3 = (this._findX(kb_pad_star) + this._findW(kb_pad_star) + gap) + "px";

    var kb_pad_minus = this._setup_key(parent, "___pad_minus", gap + "px", edge_3, cp, cp, c, lh, fs);
    kb_pad_minus.innerHTML = "-";
    this.pad[3] = kb_pad_minus;

    this.kbpM = this._findX(kb_pad_minus) + this._findW(kb_pad_minus) + gap;

    var prevH = this._findH(kb_pad_eur), edge_Y = (this._findY(kb_pad_eur) + prevH + gap) + "px";

    var kb_pad_7 = this._setup_key(parent, "___pad_7", edge_Y, edge, cp, cp, c, lh, fs);
    kb_pad_7.innerHTML = "7";
    this.pad[4] = kb_pad_7;

    var kb_pad_8 = this._setup_key(parent, "___pad_8", edge_Y, edge_1, cp, cp, c, lh, fs);
    kb_pad_8.innerHTML = "8";
    this.pad[5] = kb_pad_8;

    var kb_pad_9 = this._setup_key(parent, "___pad_9", edge_Y, edge_2, cp, cp, c, lh, fs);
    kb_pad_9.innerHTML = "9";
    this.pad[6] = kb_pad_9;

    var kb_pad_plus = this._setup_key(parent, "___pad_plus", edge_Y, edge_3, cp, dp, c, dp2, fs);
    kb_pad_plus.innerHTML = "+";
    this.pad[7] = kb_pad_plus;

    edge_Y = (this._findY(kb_pad_7) + prevH + gap) + "px";

    var kb_pad_4 = this._setup_key(parent, "___pad_4", edge_Y, edge, cp, cp, c, lh, fs);
    kb_pad_4.innerHTML = "4";
    this.pad[8] = kb_pad_4;

    var kb_pad_5 = this._setup_key(parent, "___pad_5", edge_Y, edge_1, cp, cp, c, lh, fs);
    kb_pad_5.innerHTML = "5";
    this.pad[9] = kb_pad_5;

    var kb_pad_6 = this._setup_key(parent, "___pad_6", edge_Y, edge_2, cp, cp, c, lh, fs);
    kb_pad_6.innerHTML = "6";
    this.pad[10] = kb_pad_6;

    edge_Y = (this._findY(kb_pad_4) + prevH + gap) + "px";

    var kb_pad_1 = this._setup_key(parent, "___pad_1", edge_Y, edge, cp, cp, c, lh, fs);
    kb_pad_1.innerHTML = "1";
    this.pad[11] = kb_pad_1;

    var kb_pad_2 = this._setup_key(parent, "___pad_2", edge_Y, edge_1, cp, cp, c, lh, fs);
    kb_pad_2.innerHTML = "2";
    this.pad[12] = kb_pad_2;

    var kb_pad_3 = this._setup_key(parent, "___pad_3", edge_Y, edge_2, cp, cp, c, lh, fs);
    kb_pad_3.innerHTML = "3";
    this.pad[13] = kb_pad_3;

    var kb_pad_enter = this._setup_key(parent, "___pad_enter", edge_Y, edge_3, cp, dp, c, dp2, parseFloat(fs) * 0.643, n);
    kb_pad_enter.innerHTML = "Enter";
    this.pad[14] = kb_pad_enter;

    edge_Y = (this._findY(kb_pad_1) + prevH + gap) + "px";

    var kb_pad_0 = this._setup_key(parent, "___pad_0", edge_Y, edge, dp, cp, l, lh, fs, "", 7 * mag + "px");
    kb_pad_0.innerHTML = "0";
    this.pad[15] = kb_pad_0;

    var kb_pad_period = this._setup_key(parent, "___pad_period", edge_Y, edge_2, cp, cp, c, lh, fs);
    kb_pad_period.innerHTML = ".";
    this.pad[16] = kb_pad_period;

    return this.kbpM;
  },

  _set_key_state: function(key, on, textcolor, bordercolor, bgcolor)
  {
    if(key)
    {
      var ks = key.style;
      if(ks)
      {
        if(textcolor) ks.color = textcolor;
        if(bordercolor) ks.border = "1px solid " + bordercolor;
        if(bgcolor) ks.backgroundColor = bgcolor;
      }

      this._detach_event(key, 'mousedown', this._generic_callback_proc);

      if(on)
        this._setup_event(key, 'mousedown', this._generic_callback_proc);
    }
  },

  _refresh_layout: function(layout)
  {
    if(!layout) layout = this.mod[7].innerHTML;

    var fc = this.fontcolor, kc = this.keycolor, ikc = this.ikc;
    var ibc = this.ibc, bc = this.bordercolor, lic = this.lic;

    var arr_type = this.AltGr ? (this.Shift ? "alt_gr_shift" : "alt_gr") : (this.Shift ? "shift" : (this.Caps ? "caps" : "normal"));

    var nkeys = this.keys.length;

    var norm_arr  = this[layout + "_normal"];
    var caps_arr  = this[layout + "_caps"];
    var shift_arr = this[layout + "_shift"];
    var alt_arr   = this[layout + "_alt_gr"];

    var alt_shift_arr = this[layout + "_alt_gr_shift"];

    var dead_arr = this[this.DeadAction[1]] || null;

    var bcaps  = (caps_arr  && (caps_arr.length  == nkeys));
    var bshift = (shift_arr && (shift_arr.length == nkeys));
    var balt   = (alt_arr   && (alt_arr.length   == nkeys));
    var baltsh = (balt      && alt_shift_arr && (alt_shift_arr.length == nkeys));

    var caps = this.mod[2], shift = this.mod[5], shift_r = this.mod[6], alt_gr = this.mod[11];

    if(bshift)
    {
      this._set_key_state(shift, true, fc, bc, this.Shift ? lic : kc);
      this._set_key_state(shift_r, true, fc, bc, this.Shift ? lic : kc);
    }
    else
    {
      this._set_key_state(shift, false, ibc, ibc, ikc);
      this._set_key_state(shift_r, false, ibc, ibc, ikc);

      if(arr_type == "shift")
      {
        arr_type = "normal";
        this.Shift = false;
      }
    }

    if(balt)
    {
      this._set_key_state(alt_gr, true, fc, bc, this.AltGr ? lic : kc);

      if(this.AltGr)
      {
        if(baltsh)
        {
          this._set_key_state(shift, true, fc, bc);
          this._set_key_state(shift_r, true, fc, bc);
        }
        else
        {
          this._set_key_state(shift, false, ibc, ibc, ikc);
          this._set_key_state(shift_r, false, ibc, ibc, ikc);

          arr_type = "alt_gr";
          this.Shift = false;
        }
      }
    }
    else
    {
      this._set_key_state(alt_gr, false, ibc, ibc, ikc);

      if(arr_type == "alt_gr")
      {
        arr_type = "normal";
        this.AltGr = false;
      }
      else if(arr_type == "alt_gr_shift")
      {
        arr_type = "normal";
        this.AltGr = false, this.Shift = false;

        shift.style.backgroundColor = kc, shift_r.style.backgroundColor = kc;
      }
    }

    if(this.Shift && !baltsh)
      this._set_key_state(alt_gr, false, ibc, ibc, ikc);

    if(bcaps && !this.AltGr)
      this._set_key_state(caps, true, fc, bc, this.Caps ? lic : kc);
    else
    {
      this._set_key_state(caps, false, ibc, ibc, ikc);

      this.Caps = false;
      if(arr_type == "caps") arr_type = "normal";
    }

    var arr_cur = this[layout + "_" + arr_type];

    var i = nkeys;
    while(--i >= 0)
    {
      var key = this.keys[i], key_val = arr_cur[i]; if(!key_val) key_val = "";

      if(this.Shift && this.Caps)
      {
        var key_nrm = norm_arr[i], key_cps = caps_arr[i], key_shf = shift_arr[i];

        if((key_cps == key_shf) && (key_nrm != key_cps)) key_val = key_nrm;
      }

      if(typeof(key_val) == "object")
      {
        key.innerHTML = key_val[0], key.dead = key_val[1];

        this._set_key_state(key, true, this.deadcolor, bc, (this.DeadAction[0] == key_val[0] ? lic : kc));
      }
      else
      {
        key.dead = null;

        var block = false;

        if(key_val != "")
        {
          if(dead_arr)
          {
            for(var j = 0, l = dead_arr.length; j < l; j++) { var dk = dead_arr[j]; if(dk[0] == key_val) { key_val = dk[1]; break;}};

            if(j == l) block = true;
          }

          key.innerHTML = key_val;

          if(block)
            this._set_key_state(key, false, ibc, ibc, ikc);
          else
            this._set_key_state(key, true, fc, bc, kc);
        }
        else
        {
          key.innerHTML = "&nbsp;";
          this._set_key_state(key, false, ibc, ibc, ikc);
        }
      }
    }

    i = this.mod.length;
    while(--i >= 0)
    {
      var key = this.mod[i];

      switch(i)
      {
        case 2: case 5: case 6: case 11:
          break;

        case 7:
          key.innerHTML = layout;

          this._detach_event(key, 'mousedown', this._handle_lang_menu);

          if(this.DeadAction[1])
            this._set_key_state(key, false, ibc, ibc, ikc);
          else
          {
            var many = (this.avail_langs.length > 1);

            this._set_key_state(key, false, fc, many ? this.lsc : ibc, many ? kc : ikc);
            if(many)
              this._setup_event(key, 'mousedown', this._handle_lang_menu);
          }
          break;

        case 10:
          key.innerHTML = this.DeadAction[1] ? this.DeadAction[0] : "&nbsp;";

        default:
          if((this.DeadAction[1] && (i != 10)) || ((i == 8) || (i == 9) || (i == 12) || (i ==13)))
            this._set_key_state(key, false, ibc, ibc, ikc);
          else
            this._set_key_state(key, true, fc, bc, kc);

          var ks = key.style;
          switch(i)
          {
            case 4: ks.borderBottomColor = kc; break;

            case 8: case 9: case 12: case 13: ks.borderColor = ibc; break;
          }
      }
    }

    i = this.pad.length;
    while(--i >= 0)
    {
      key = this.pad[i];

      if(this.DeadAction[1])
        this._set_key_state(key, false, ibc, ibc, ikc);
      else
        this._set_key_state(key, true, fc, bc, kc);
    }
  },

  _handle_lang_menu: function(event)
  {
    var in_el = VKeyboard.prototype._get_event_source(event);
    var container_id = in_el.id.substring(0, in_el.id.indexOf("___"));
    var vkb = VKeyboard.kbArray[container_id];

    var ct = vkb.Cntr, menu = vkb.menu;

    if(menu)
    { ct.removeChild(menu); vkb.menu = null; }
    else
    {
      var fs = vkb.fontsize, kc = vkb.keycolor, bc = "1px solid " + vkb.bordercolor;

      var pad = vkb.pad.length, per_row = pad ? 5 : 4, item_wd = pad ? 108 : 103;
      var num_rows = Math.ceil(vkb.avail_langs.length / per_row);

      var mag = parseFloat(fs) / 14.0, cell = Math.floor(25.0 * mag), cp = cell + "px", lh = (cell - 2) + "px", w = item_wd * mag;
      var h1 = Math.floor(cell + mag), h2 = String(w - mag) + "px", pad = String(4 * mag) + "px", wd = String(w * per_row + 1) + "px";

      var langs = vkb.avail_langs.length;

      menu = document.createElement("DIV"); var ms = menu.style;
      ms.display  = "block";
      ms.position = "relative";

      ms.top = "1px", ms.left = "0px";
      ms.width = wd;
      ms.border = bc;
      ms.backgroundColor = vkb.bgcolor;

      vkb.menu = ct.appendChild(menu);

      var menu_main = document.createElement("DIV"); ms = menu_main.style;
      ms.fontFamily = vkb.fontname;
      ms.position   = "relative";

      ms.color  = vkb.fontcolor;
      ms.width  = wd;
      ms.height = String(num_rows * h1 + 1) + "px";
      ms.cursor = "default";

      menu.appendChild(menu_main);

      function setcolor(obj, c) { return function() { obj.style.backgroundColor = c; } };

      for(var j = 0; j < langs; j++)
      {
        var item = vkb._setup_key(menu_main, "___lang_" + String(j), String(h1 * Math.floor(j / per_row) + 1) + "px", String((j % per_row) * w + 1) + "px", h2, cp, "center", lh, fs, "normal", pad);
        item.style.backgroundColor = kc;
        item.style.border = bc;
        item.innerHTML = vkb.avail_langs[j][1];

        vkb._setup_event(item, 'mousedown', vkb._handle_lang_item);
        vkb._setup_event(item, 'mouseover', setcolor(item, vkb.lic));
        vkb._setup_event(item, 'mouseout',  setcolor(item, kc));
      }
    }
  },

  _handle_lang_item: function(event)
  {
    var in_el = VKeyboard.prototype._get_event_source(event);
    var container_id = in_el.id.substring(0, in_el.id.indexOf("___"));
    var vkb = VKeyboard.kbArray[container_id];

    var ndx = in_el.id.indexOf("___lang_");
    var lng = in_el.id.substring(ndx + 8, in_el.id.length);
    var newl = vkb.avail_langs[lng][0];

    if(vkb.mod[7].innerHTML != newl)
      vkb._refresh_layout(newl);

    vkb.Cntr.removeChild(vkb.menu);
    vkb.menu = null;
  },

  _generic_callback_proc: function(event)
  {
    var in_el = VKeyboard.prototype._get_event_source(event);
    var container_id = in_el.id.substring(0, in_el.id.indexOf("___"));
    var vkb = VKeyboard.kbArray[container_id];

    var val = in_el.subst || in_el.innerHTML;
    if(!val) return;
	
    switch(val)
    {
      case "Caps": case "Shift": case "AltGr":

        vkb[val] = !vkb[val];
        vkb._refresh_layout();

        if(vkb.sc) vkb._start_flash(in_el);
        return;

      case "Tab":    
      	{
      	
      	switch(text.name){
      		case "customerId":      			      			
      			document.all.customerPassword.focus();    
      			setCurrentField(document.all.customerPassword);        			   			
      			break;
      		case "customerPassword":      			      			
      			document.all.captcha.focus();    
      			setCurrentField(document.all.captcha);        			   			
      			break;
      		case "captcha":
      		   	document.all.customerId.focus(); 
				setCurrentField(document.all.customerId)
      			break;       			       	
      	  	     		   	     		   	     		   	   		
      	}
      	val = ""; break;
      }
      case "&nbsp;": val = " ";  break;
      case "&quot;": val = "\""; break;
      case "&lt;":   val = "<";  break;
      case "&gt;":   val = ">";  break;
      case "&amp;":  val = "&";  break;
    }

    if(vkb.sc) vkb._start_flash(in_el);

    if(in_el.dead)
    {
      if(in_el.dead == vkb.DeadAction[1])
      { val = ""; vkb.DeadAction[0] = vkb.DeadAction[1] = null; }
      else
      { vkb.DeadAction[0] = val; vkb.DeadAction[1] = in_el.dead; }

      vkb._refresh_layout();
      return;
    }
    else
    {
	  var r;
      if(vkb.DeadAction[1]) { vkb.DeadAction[0] = vkb.DeadAction[1] = null; r = true; }

      if(vkb.AltGr || vkb.Shift || r)
      {
        vkb.AltGr = false; vkb.Shift = false;
        vkb._refresh_layout();
      }
    }

    if(vkb._Callback) vkb._Callback(val, vkb.Cntr.id);
  },

  SetParameters: function()
  {
    var l = arguments.length;
    if(!l || (l % 2 != 0)) return false;

    var p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;

    while(--l > 0)
    {
      var value = arguments[l];

      switch(arguments[--l])
      {
        case "callback":
          p0 = ((typeof(value) == "function") && ((value.length == 1) || (value.length == 2))) ? value : this._Callback;
          break;

        case "font-name":  p1 = value; break;
        case "font-size":  p2 = value; break;
        case "font-color": p3 = value; break;
        case "dead-color": p4 = value; break;
        case "base-color": p5 = value; break;
        case "key-color":  p6 = value; break;

        case "selection-color": p7 = value; break;
        case "border-color":    p8 = value; break;

        case "inactive-border-color": p9  = value; break;
        case "inactive-key-color":    p10 = value; break;
        case "lang-cell-color":       p11 = value; break;

        case "show-click": p12 = value; break;

        case "click-font-color":   p13 = value; break;
        case "click-key-color":    p14 = value; break;
        case "click-border-color": p15 = value; break;

        case "layout": p16 = value; break;

        default: break;
      }
    }

    this._construct(this.Cntr.id, p0, 0, 0, 0, (this.pad.length != 0), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, this.gap, p16);

    return true;
  },

  Show: function(value)
  {
    var ct = this.Cntr.style;

    ct.display = ((value == undefined) || (value == true)) ? "block" : ((value == false) ? "none" : ct.display);
  },

  ShowNumpad: function(value)
  {
    var sh = ((value == undefined) || (value == true)) ? "block" : ((value == false) ? "none" : null);
    if(!sh) return;

    var kb = this.Cntr.childNodes[0];

    var i = this.pad.length;
    if(i)
    {
      while(--i >= 0)
        this.pad[i].parentNode.style.display = sh;

      kb.style.width = kb.childNodes[0].style.width = (sh == "none") ? (this.kbpH + 1) + "px" : this.kbpM + "px";
    }
    else
    {
      if(sh == "block")
      {
        kb.style.width = kb.childNodes[0].style.width = this._create_numpad(this.Cntr.id, kb.childNodes[0]);
        this._refresh_layout();
      }
    }
  },

  // Layout info:

  avail_langs: [["Us", "English (US)"]],

  // Us International:

  Us_normal: ["&#x0040;", "&#x0031;", "&#x0032;", "&#x0033;", "&#x0034;", "&#x0035;", "&#x0036;", "&#x0037;", "&#x0038;", "&#x0039;", "&#x0030;", "&#x002D;", "&#x003D;",
              "&#x0071;", "&#x0077;", "&#x0065;", "&#x0072;", "&#x0074;", "&#x0079;", "&#x0075;", "&#x0069;", "&#x006F;", "&#x0070;", "&#x005B;", "&#x005D;", "&#x005C;",
              "&#x0061;", "&#x0073;", "&#x0064;", "&#x0066;", "&#x0067;", "&#x0068;", "&#x006A;", "&#x006B;", "&#x006C;", "&#x003B;", "&#x0027;",,
              "&#x007A;", "&#x0078;", "&#x0063;", "&#x0076;", "&#x0062;", "&#x006E;", "&#x006D;", "&#x002C;", "&#x002E;", "&#x002F;"],

  Us_caps: ["&#x0040;", "&#x0031;", "&#x0032;", "&#x0033;", "&#x0034;", "&#x0035;", "&#x0036;", "&#x0037;", "&#x0038;", "&#x0039;", "&#x0030;", "&#x002D;", "&#x003D;",
            "&#x0051;", "&#x0057;", "&#x0045;", "&#x0052;", "&#x0054;", "&#x0059;", "&#x0055;", "&#x0049;", "&#x004F;", "&#x0050;", "&#x005B;", "&#x005D;", "&#x005C;",
            "&#x0041;", "&#x0053;", "&#x0044;", "&#x0046;", "&#x0047;", "&#x0048;", "&#x004A;", "&#x004B;", "&#x004C;", "&#x003B;", "&#x0027;",,
            "&#x005A;", "&#x0058;", "&#x0043;", "&#x0056;", "&#x0042;", "&#x004E;", "&#x004D;", "&#x002C;", "&#x002E;", "&#x002F;"]

 
};