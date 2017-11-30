package top.u8u.db.domain;

/**
 * Created by dim on 17-10-15.
 */
public class BlogContentTemplate {
    //临时存放暂时未实现模板功能，临时提供上级模块调用
    private static final String defaultContent ="<!DOCTYPE html>\n" +
            "<html lang=\"zh_CN\">\n" +
            "<head>\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n" +
            "    <link rel=\"shortcut icon\" href=\"../../img/favicon.ico\" type=\"image/x-icon\"/>\n" +
            "    <meta name=\"robots\" content=\"all\">\n" +
            "    <meta name=\"author\" content=\"Kaifan,z@zhoukaifan.com\">\n" +
            "    <meta name=\"copyright\" content=\"Kaifan\">\n" +
            "    <meta name=\"keywords\" content=\"Kaifan,博客,后端,JAVA,HTML,J2EE,JAVA WEB,JS,C/C++,大学,程序员,$[typeName],$[date],$[keyWord]\">\n" +
            "    <meta name=\"description\" content=\"$[digest]\">\n" +
            "    <title>$[title]|Kaifan & Blog</title>\n" +
            "    <!-- CSS  -->\n" +
            "    <link href=\"//fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n" +
            "    <link href=\"../../css/materialize.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\n" +
            "    <link href=\"../../css/style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\n" +
            "    <link href=\"//cdn.bootcss.com/highlight.js/8.5/styles/vs.min.css\" rel=\"stylesheet\">\n" +
            "    <script src=\"//cdn.bootcss.com/highlight.js/8.5/highlight.min.js\"></script>\n" +
            "    <script src=\"//code.jquery.com/jquery-2.1.1.min.js\"></script>\n" +
            "    <script src=\"../../js/app.js\"></script>\n" +
            "    <script src=\"../../layer/layer.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "        var ID = '$[id]';\n" +
            "        hljs.initHighlighting.called = false;\n" +
            "    </script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<ul id=\"typeMenu\" class=\"dropdown-content\"></ul>\n" +
            "<ul id=\"dateMenu\" class=\"dropdown-content\"></ul>\n" +
            "<nav class=\"white\" role=\"navigation\">\n" +
            "    <div class=\"nav-wrapper container\">\n" +
            "        <a id=\"logo-container\" href=\"../../index.html\" class=\"brand-logo\"><b>Kaifan</b>&Blog</a>\n" +
            "        <ul class=\"right hide-on-med-and-down\">\n" +
            "            <li><a href=\"../../index.html\">主页</a></li>\n" +
            "            <li><a href=\"../../contactMe.html\">留言</a></li>\n" +
            "            <li><a class=\"dropdown-button\" href=\"#!\" data-activates=\"typeMenu\">文章分类<i class=\"material-icons right\">arrow_drop_down</i></a>\n" +
            "            </li>\n" +
            "            <li><a class=\"dropdown-button\" href=\"#!\" data-activates=\"dateMenu\">历史文章<i class=\"material-icons right\">arrow_drop_down</i></a>\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "\n" +
            "        <ul id=\"nav-mobile\" class=\"collapsible side-nav\" data-collapsible=\"accordion\">\n" +
            "            <li><a href=\"../../index.html\">主页</a></li>\n" +
            "            <li><a href=\"../../contactMe.html\">留言</a></li>\n" +
            "            <li>\n" +
            "                <a class=\"collapsible-header\" style=\"padding-left: 30px\">文章分类</a>\n" +
            "                <div class=\"collapsible-body\">\n" +
            "                    <ul id=\"typeMenuCollapsible\">\n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "            </li>\n" +
            "            <li>\n" +
            "                <a class=\"collapsible-header\" style=\"padding-left: 30px\">历史文章</a>\n" +
            "                <div class=\"collapsible-body\">\n" +
            "                    <ul id=\"dateMenuCollapsible\">\n" +
            "                    </ul>\n" +
            "                </div>\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "        <a href=\"#\" data-activates=\"nav-mobile\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\n" +
            "    </div>\n" +
            "</nav>\n" +
            "<div id=\"index-banner\" class=\"parallax-container\">\n" +
            "    <div class=\"section no-pad-bot\">\n" +
            "        <div class=\"container\">\n" +
            "            <br><br>\n" +
            "            <h1 class=\"header center grey-text text-lighten-3 \">Kaifan & Blog</h1>\n" +
            "            <div class=\"row center\">\n" +
            "                <h5 class=\"header col s12 light white-text\">编程、学习、娱乐、捣蛋，生命在于折腾。</h5>\n" +
            "            </div>\n" +
            "            <div class=\"row center\">\n" +
            "                <a href=\"javascript:getBio()\" id=\"download-button\"\n" +
            "                   class=\"btn-large waves-effect waves-light grey lighten-1\">获取简历</a>\n" +
            "            </div>\n" +
            "            <br><br>\n" +
            "\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"parallax\"><img src=\"../../img/office.jpg\" alt=\"Unsplashed background img 1\"></div>\n" +
            "</div>\n" +
            "<div class=\"container\">\n" +
            "    <div class=\"section\">\n" +
            "        <!--   Icon Section   -->\n" +
            "        <h2>$[title]</h2>\n" +
            "        <h5>时间：$[date] | 分类：$[typeName] | 类型：$[originalStr]文章 | 阅读：<span id=\"readCount\">0</span>人阅读</h5>\n" +
            "        <br/><hr/>\n" +
            "        <div>$[content]</div>\n" +
            "        <br/><!--<hr/>-->\n" +
            "        <div class=\"card-panel\">\n" +
            "            <h5 id=\"quoteFlag\">发表评论:</h5>\n" +
            "            <div class=\"row\">\n" +
            "                <div class=\"input-field col s12\">\n" +
            "                    <i class=\"material-icons prefix\">account_circle</i>\n" +
            "                    <input id=\"name\" name=\"name\" type=\"text\" class=\"validate\">\n" +
            "                    <label for=\"icon_prefix\">Name</label>\n" +
            "                </div>\n" +
            "                <div class=\"input-field col s12\">\n" +
            "                    <i class=\"material-icons prefix\">mode_edit</i>\n" +
            "                    <textarea id=\"content\" name=\"content\" class=\"materialize-textarea\"></textarea>\n" +
            "                    <label for=\"icon_prefix2\">Content</label>\n" +
            "                </div>\n" +
            "                <div class=\"input-field col s12 center\">\n" +
            "                    <a href=\"javascript:pushComment()\" class=\"waves-effect waves-light btn-large grey lighten-1\"><i\n" +
            "                            class=\"material-icons left\">cloud</i>发表评论</a>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div id=\"commentContent\"></div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<footer class=\"page-footer  grey lighten-3 \">\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col l6 s12\">\n" +
            "                <h5 class=\"black-text\">Bio</h5>\n" +
            "                <p class=\"black-text text-lighten-4\">\n" +
            "                    全栈开发，主学后端，没事瞎折腾。<br/>\n" +
            "                    联系方式：请看右边———><br/>\n" +
            "                    这是啥？一个无聊的人，做的一个无聊的网站，写一些无聊的内容。\n" +
            "                </p>\n" +
            "            </div>\n" +
            "            <div class=\"col l3 s12\">\n" +
            "                <h5 class=\"black-text\">Contact me</h5>\n" +
            "                <ul>\n" +
            "                    <li><a class=\"black-text\" href=\"mailto:z@zhoukaifan.com\">E-mail</a></li>\n" +
            "                    <li><a class=\"black-text\" href=\"../../contactMe.html\" target=\"_blank\">在线留言</a></li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "            <div class=\"col l3 s12\">\n" +
            "                <h5 class=\"black-text\">Follow me</h5>\n" +
            "                <ul>\n" +
            "                    <li><a class=\"black-text\" href=\"https://github.com/zkaif\" target=\"_blank\">Github</a></li>\n" +
            "                    <li><a class=\"black-text\" href=\"https://www.zhihu.com/people/chen-kai-17-56/activities\"\n" +
            "                           target=\"_blank\">知乎</a></li>\n" +
            "                    <li><a class=\"black-text\" href=\"http://www.linkedin.com/in/%E5%87%AF%E5%B8%86-%E5%91%A8-5a19b1142/\"\n" +
            "                           target=\"_blank\">领英</a>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"footer-copyright\">\n" +
            "        <div class=\"container black-text\">\n" +
            "            <div>© 2017 <a class=\"grey-text text-darken-2\" href=\"#!\">zhoukaifan.com</a></div>\n" +
            "            <div><span>ICP证：京ICP备17019906号</span></div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</footer>\n" +
            "<!--  Scripts-->\n" +
            "<script src=\"../../js/materialize.js\"></script>\n" +
            "<script src=\"../../js/blogSingle.js\"></script>\n" +
            "<script src=\"../../js/init.js\"></script>\n" +
            "</body>\n" +
            "</html>\n" +
            "  \n";

    private String name;
    private String content;

    public BlogContentTemplate() {
        this.name="default";
        this.content=defaultContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
