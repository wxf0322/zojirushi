<%--
  Created by IntelliJ IDEA.
  User: wangxiaofeng
  Date: 18/5/16
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<c:url value='/assets/dist/img/user2-160x160.jpg'/>" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>管理员</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="搜索...">
                <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">系统管理</li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>基础数据管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="active"><a href="/index"><i class="fa fa-user"></i> 用户管理</a></li>
                    <li><a href="/index"><i class="fa a-group"></i> 角色管理</a></li>
                    <li><a href="/index"><i class="fa fa-unlock-alt"></i> 权限管理</a></li>
                    <li><a href="/index"><i class="fa fa-circle-o"></i> 机构管理</a></li>
                </ul>
            </li>
            <%--<li><a href="https://adminlte.io/docs"><i class="fa fa-book"></i> <span>Documentation</span></a></li>--%>
            <%--<li class="header">LABELS</li>--%>
            <%--<li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>--%>
            <%--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>--%>
            <%--<li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>--%>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
</body>
</html>
