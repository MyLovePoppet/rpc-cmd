(this.webpackJsonpdemo=this.webpackJsonpdemo||[]).push([[0],{120:function(e,t,n){},192:function(e,t,n){"use strict";n.r(t);var c=n(0),r=n.n(c),a=n(24),o=n.n(a),s=n(37),i=(n(120),n(58)),l=n(50),j=n.n(l),u=n(66),b=n(110),d=n(195),p=n(196),O=n(108),h=Object(c.createContext)(null),m=n(14),f={labelCol:{span:4},wrapperCol:{span:16}},x={wrapperCol:{offset:4,span:16}},g=function(){var e=Object(c.useState)(!1),t=Object(s.a)(e,2),n=t[0],r=t[1],a=Object(c.useContext)(h).setIsLogin;Object(c.useEffect)((function(){localStorage.getItem("token")&&a(!0)}),[a]);return Object(m.jsxs)(d.a,Object(i.a)(Object(i.a)({},f),{},{hideRequiredMark:!0,name:"basic",initialValues:{remember:!0},onFinish:function(e){r(!0),fetch("".concat("http://172.30.67.81:8080","/doConnect"),{method:"post",headers:{"Content-Type":"application/json"},body:JSON.stringify(e)}).then(function(){var e=Object(u.a)(j.a.mark((function e(t){var n,c,o,s;return j.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(!t.ok){e.next=11;break}return e.next=3,t.json();case 3:n=e.sent,c=n.code,o=n.message,s=n.token,r(!1),0===c?(b.b.success(o),localStorage.setItem("token",s),a(!0)):b.b.error(o),e.next=13;break;case 11:window.alert("\u767b\u5f55\u5931\u8d25"),r(!1);case 13:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}())},onFinishFailed:function(e){console.log("Failed:",e)},children:[Object(m.jsx)(d.a.Item,{label:"IP\u5730\u5740",name:"host",rules:[{required:!0,message:"\u8bf7\u8f93\u5165IP\u5730\u5740!"}],children:Object(m.jsx)(p.a,{})}),Object(m.jsx)(d.a.Item,{label:"\u7aef\u53e3\u53f7",name:"port",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7aef\u53e3\u53f7!"}],children:Object(m.jsx)(p.a,{})}),Object(m.jsx)(d.a.Item,Object(i.a)(Object(i.a)({},x),{},{children:Object(m.jsx)(O.a,{type:"primary",htmlType:"submit",loading:n,children:"Submit"})}))]}))},k=n(109),v=n(194),y=p.a.Search,S=function(){var e=Object(c.useState)([]),t=Object(s.a)(e,2),n=t[0],r=t[1],a=Object(c.useState)(!1),o=Object(s.a)(a,2),i=o[0],l=o[1],d=Object(c.useContext)(h).setIsLogin;return Object(m.jsx)(v.a,{title:Object(m.jsx)("div",{children:Object(m.jsx)(y,{onSearch:function(e){l(!0),fetch("".concat("http://172.30.67.81:8080","/doExec"),{method:"post",headers:{"Content-Type":"application/json"},body:JSON.stringify({cmd:e,token:localStorage.getItem("token")})}).then(function(){var t=Object(u.a)(j.a.mark((function t(c){var a,o,s,i;return j.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(!c.ok){t.next=11;break}return t.next=3,c.json();case 3:a=t.sent,o=a.code,s=a.message,i=a.res,0===o?(b.b.success(s),r([{str:i,cmd:e,id:e+n.length}].concat(Object(k.a)(n)))):(b.b.error(s),100===o&&(localStorage.removeItem("token"),d(!1))),l(!1),t.next=13;break;case 11:b.b.error("\u8bf7\u6c42\u5931\u8d25"),l(!1);case 13:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}())},placeholder:"input command",enterButton:"Send",loading:i})}),extra:Object(m.jsx)(O.a,{type:"link",onClick:function(e){localStorage.removeItem("token"),d(!1)},children:"Logout"}),children:Object(m.jsx)(v.a,{title:"Infomation",extra:Object(m.jsx)(O.a,{type:"link",onClick:function(e){r([])},children:"clear"}),children:n.map((function(e){return Object(m.jsx)(v.a,{style:{marginTop:16},type:"inner",title:e.cmd,extra:Object(m.jsx)(O.a,{type:"link",onClick:function(t){var c=n.findIndex((function(t){return t.id===e.id}));console.log(c);var a=JSON.parse(JSON.stringify(n));a.splice(c,1),r(a)},children:"delete"}),children:Object(m.jsx)("article",{children:e.str.split("\n").map((function(e){return Object(m.jsx)("p",{children:e})}))})},e.id)}))})})};var I=function(){var e=Object(c.useState)(!1),t=Object(s.a)(e,2),n=t[0],r=t[1];return Object(m.jsx)(h.Provider,{value:{isLogin:n,setIsLogin:r},children:n?Object(m.jsx)(S,{}):Object(m.jsx)(g,{})})};n(191);o.a.render(Object(m.jsx)(r.a.StrictMode,{children:Object(m.jsx)(I,{})}),document.getElementById("root"))}},[[192,1,2]]]);
//# sourceMappingURL=main.0b2e8c04.chunk.js.map