function addMenu(menuList) {
    console.log(menuList);
    var menuSide = document.getElementById('menu');
    for (var i = 0, len = menuList.length; i < len; i++) {
        var mainMenuItem = menuList[i];
        console.log(mainMenuItem);
        var name = mainMenuItem['menuName'];
        var icon = mainMenuItem['icon'];
        var li = addMainMenu(menuSide, name, icon);
        var childrenMenuItems = mainMenuItem['children'];
        console.log("childrenMenuItem:", childrenMenuItems);
        if (childrenMenuItems != null) {
            for (var j = 0; j < childrenMenuItems.length; ++j) {
                var childMenuItem = childrenMenuItems[j];
                console.log("childMenuItem:", childMenuItem);
                name = childMenuItem['menuName'];
                icon = childMenuItem['icon'];
                var url = childMenuItem['url'];
                console.log(name, icon);
                addChildMenu(li, name, url, icon);
            }
        }
    }
    layui.element.init();
}

function addMainMenu(parent, name, icon = '') {
    var li = document.createElement('li');
    li.className = 'layui-nav-item';
    var a = document.createElement('a');
    a.setAttribute('href', 'javascript:;');
    //设置主菜单的名称
    var span = document.createElement('span');
    span.innerText = name;
    var i = document.createElement('i');
    if (icon.startsWith('fa')) {
        i.className = 'fa ' + icon + ' fa-fw';
    } else {
        i.className = 'layui-icon ' + icon;
    }
    //a中添加i
    a.appendChild(i);
    a.appendChild(span);
    //创建dl
    var dl = document.createElement('dl');
    dl.className = 'layui-nav-child';
    //li中添加a
    li.appendChild(a);
    //li中添加dl
    li.appendChild(dl);
    parent.appendChild(li);
    return dl;
}

function addChildMenu(parent, name = '', url = '', icon = '') {
    console.log("childMenu:" + name);
    var dd = document.createElement('dd');
    var a = document.createElement('a');
    a.setAttribute('href', 'javascript:;');
    a.setAttribute('href-url', url);
    var span = document.createElement('span');
    span.innerText = name;
    var i = document.createElement('i');
    if (icon.startsWith('fa')) {
        i.className = 'fa ' + icon + ' fa-fw';
    } else {
        i.className = 'layui-icon ' + icon;
    }
    a.appendChild(i);
    a.appendChild(span);
    dd.appendChild(a);
    parent.appendChild(dd);
}