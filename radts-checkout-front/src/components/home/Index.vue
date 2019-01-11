<template>
  <div class="index-container">
    <el-row class="el-fullheight el-header">
      <el-col :span="1" class="el-fullheight" style="text-align:center;line-height:50px;cursor: pointer;background-color: #0a598e;">
        <span  @click="handleOpenShow" style="display:block">
          <i class="iconfont el-show-color" :class="[isZheDie1, isZheDie]"></i>
        </span>
      </el-col>
      <el-col :span="4" class="el-fullheight logo">RADTS-CHECKOUT</el-col>
      <el-col :span="4" class="el-fullheight user">
          <i class="iconfont icon-ren1-copy"></i>
          <span>管理员</span>
          <span class="t-left10">|</span>
          <span class="logout" @click="logOut">
              <i class="iconfont icon-tuichu" title="退出"></i>
          </span>
      </el-col>
    </el-row>
    <el-row class="el-fullheight">
      <el-col :span="menuSpans" class="el-fullheight">
        <el-menu :default-active="activeIndex" class="el-menu-vertical el-menu-collapse-vertical" @open="handleOpen" @close="handleClose" :collapse="isCollapse" :unique-opened="true" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
            <el-submenu v-for="(item,index) in menuList" :key="index" :index='item.id'>
                <template slot="title">
                    <i :class="item.icon"></i>
                    <span>{{item.title}}</span>
                </template>
                <el-menu-item-group >
                    <el-menu-item v-for="child in item.children" :key="child.id" :index='child.index' :route='{path: child.index}' @click="handleRout(child.index)">{{child.title}}</el-menu-item>
                </el-menu-item-group>
            </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="24-menuSpans" class="el-fullheight">
            <el-tabs
                v-model="activeIndex"
                type="border-card"
                v-if="openTab.length"
                @tab-click='tabClick'
                @tab-remove='tabRemove'
                >
                <el-tab-pane
                  :key="item.name"
                  v-for="(item) in openTab"
                  :label="item.name"
                  :name="item.route"
                  :closable="item.closable"
                 >
                </el-tab-pane>
            </el-tabs>
            <div class="el-fullheight">
              <keep-alive>
                <router-view v-if="$route.meta.keepAlive"></router-view>
              </keep-alive>
              <router-view v-if="!$route.meta.keepAlive"></router-view>
            </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { CookieUtil } from '@/utils/tools'

export default {
  name: 'Index',
  data () {
    return {
      editableTabsValue: '1',
      tabIndex: 1,
      editableTabs: [],
      isCollapse: false,
      isZheDie: '',
      isZheDie1: 'icon-zhedie',
      menuSpans: 3,
      isActive: '',
      menuList: [
        {
          id: '0',
          title: '设置',
          icon: 'el-icon-setting',
          children: [
            {
              id: '01',
              index: '/manage/instructions',
              title: '使用说明'
            },
            {
              id: '02',
              index: '/manage/setting',
              title: '参数设置'
            }
          ]
        },
        {
          id: '1',
          title: '预约挂号',
          icon: 'iconfont icon-registration1 icon-right',
          children: [
            {
              id: '11',
              index: '/yygh/yishengjsxx',
              title: '医生介绍信息'
            },
            {
              id: '12',
              index: '/manage/page1',
              title: '导航1page1'
            }
          ]
        },
        {
          id: '2',
          title: '挂号结算',
          icon: 'iconfont icon-gaiicon- icon-right',
          children: [
            {
              id: '21',
              index: '/manage/page2',
              title: '导航2page2'
            }
          ]
        },
        {
          id: '3',
          title: '预约检查',
          icon: 'iconfont icon-examine icon-right',
          children: [
            {
              id: '31',
              index: '/manage/page3',
              title: '导航2page3'
            }
          ]
        },
        {
          id: '4',
          title: '预约检验',
          icon: 'iconfont icon-checkout2 icon-right',
          children: [
            {
              id: '41',
              index: '/manage/page4',
              title: '导航2page4'
            }
          ]
        },
        {
          id: '5',
          title: '预约住院',
          icon: 'iconfont icon-in_hospital icon-right',
          children: [
            {
              id: '41',
              index: '/manage/page4',
              title: '导航2page4'
            }
          ]
        },
        {
          id: '6',
          title: '预约手术',
          icon: 'iconfont icon-surgery icon-right',
          children: [
            {
              id: '41',
              index: '/manage/page4',
              title: '导航2page4'
            }
          ]
        }
      ]
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    handleOpenShow () {
      this.isCollapse = !this.isCollapse
      if (this.isZheDie === '') {
        this.isZheDie = 'icon-zhedie1'
        this.isZheDie1 = ''
        this.menuSpans = 1
      } else {
        this.isZheDie = ''
        this.isZheDie1 = 'icon-zhedie'
        this.menuSpans = 3
      }
    },
    logOut () {
      this.$confirm('是否继续退出?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        this.$store.state.openTab.length = 0
        CookieUtil.delCookie('radts_token')
        this.$router.push({ path: '/' })
      }).catch(() => {})
    },
    addTab (targetName) {
      let itemTab = this.editableTabs.find((value, index, arr) => {
        return value.name === targetName
      })
      if (itemTab !== undefined) {
        this.editableTabsValue = itemTab.name
        return
      }
      let newTabName = targetName
      this.editableTabs.push({
        title: targetName,
        name: newTabName,
        tabsClose: true,
        content: 'New Tab content'
      })
      this.editableTabsValue = newTabName
    },
    removeTab (targetName) {
      let tabs = this.editableTabs
      let activeName = this.editableTabsValue
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1]
            if (nextTab) {
              activeName = nextTab.name
            }
          }
        })
      }
      this.editableTabsValue = activeName
      this.editableTabs = tabs.filter(tab => tab.name !== targetName)
    },
    handleRout (index) {
      this.$router.push(index)
    },
    // tab标签点击时，切换相应的路由
    tabClick (tab) {
      console.log(tab)
      console.log(this.activeIndex)
      this.$router.push({path: this.activeIndex})
    },
    // 移除tab标签
    tabRemove (targetName) {
      console.log('tabRemove', targetName)
      // 首页不删
      // if(targetName == '/manage/instructions'){
      //   return
      // }
      this.$store.commit('delete_tabs', targetName)
      if (this.activeIndex === targetName) {
        // 设置当前激活的路由
        if (this.openTab && this.openTab.length >= 1) {
          console.log('=============', this.openTab[this.openTab.length - 1].route)
          this.$store.commit('set_active_index', this.openTab[this.openTab.length - 1].route)
          this.$router.push({path: this.activeIndex})
        } else {
          this.$router.push({path: '/index'})
        }
      }
    }
  },
  mounted () {
    // 刷新时以当前路由做为tab加入tabs
    // 当前路由不是首页时，添加首页以及另一页到store里，并设置激活状态
    // 当当前路由是首页时，添加首页到store，并设置激活状态
    console.log(this.$route.path)
    // 刷新时以当前路由做为tab加入tabs
    let instructions = {route: '/manage/instructions', name: '使用说明', closable: false}
    if (this.$route.path !== '/index' && this.$route.path !== '/manage/instructions') {
      console.log('1')
      this.$store.commit('add_tabs', instructions)
      this.$store.commit('add_tabs', {route: this.$route.path, name: this.$route.name, closable: true})
      this.$store.commit('set_active_index', this.$route.path)
    } else {
      console.log('2')
      this.$store.commit('add_tabs', instructions)
      this.$store.commit('set_active_index', '/manage/instructions')
      this.$router.push('/index')
    }
  },
  computed: {
    openTab () {
      return this.$store.state.openTab
    },
    activeIndex: {
      get () {
        return this.$store.state.activeIndex
      },
      set (val) {
        this.$store.commit('set_active_index', val)
      }
    }
  },
  watch: {
    $route (to, from) {
      // console.log('routeTo', to.path)
      // 判断路由是否已经打开
      // 已经打开的 ，将其置为active
      // 未打开的，将其放入队列里
      let flag = false
      for (let item of this.openTab) {
        // console.log('item.name', item.name)
        // console.log('t0.name', to.name)

        if (item.name === to.name) {
          // console.log('to.path', to.path)
          this.$store.commit('set_active_index', to.path)
          flag = true
          break
        }
      }

      if (!flag) {
        // console.log('to.path', to.path)
        if (to.name === '使用说明') {
          this.$store.commit('add_tabs', {route: to.path, name: to.name, closable: false})
        } else {
          this.$store.commit('add_tabs', {route: to.path, name: to.name, closable: true})
        }
        this.$store.commit('set_active_index', to.path)
      }
    }
  }
}

</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$bg2:#fff;

.el-menu-item {
      height: 40px;
      line-height: 40px;
      padding: 0 20px;
      min-width: 178px;
}

.index-container {
    position: fixed;
    height: 100%;
    width:100%;
    background-color: $bg2;
    input:-webkit-autofill {
      -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
      -webkit-text-fill-color: #fff !important;
    }
    .el-fullheight {
        height: inherit;
    }
    .el-header{
        height: 50px;
        background-color: #299eed;
        -webkit-text-fill-color: #fff !important;
        padding: 0;
    }
    .t-left10 {
          margin-left: 10px;
    }
    .logo {
        width: auto;
        float: left;
        background: url(../../../static/img/logo.png) no-repeat left -64px;
        padding-left: 160px;
        box-sizing: border-box;
        color: #fff;
        line-height: 50px;
        font-size: 20px;
    }
    .user{
        width: auto;
        float: right;
        padding-right: 100px;
        box-sizing: border-box;
        line-height: 50px;
        font-size: 14px;
        color: #fff;
    }
    .logout {
        height: 48px;
        width: 40px;
        margin-left: 12px;
        font-size: 16px;
        color: #fff;
        cursor: pointer;
    }
    .el-menu-vertical:not(.el-menu--collapse) {
        // width: 180px;
        height: 100%;
        min-height: 400px;
    }
    .el-menu-collapse-vertical {
        height: 100%;
    }
    .el-submenu .el-menu-item {
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        min-width: 178px;
    }
    .el-show-color {
        color: rgb(255, 208, 75);
    }
    .el-tabs__content {
      display: none;
    }
    .el-menu--collapse {
      width: auto;
    }
    div.el-col-1 .el-submenu__title {
      font-size: 14px;
      color: #303133;
      padding: 0 20px;
      cursor: pointer;
      -webkit-transition: border-color .3s,background-color .3s,color .3s;
      transition: border-color .3s,background-color .3s,color .3s;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
      text-align: center;
    }
    .icon-right:before {
      margin-right: 8px;
    }
    // .el-tabs-color {
    //     background-color: rgb(84, 92, 100);
    //     color: #fff;
    // }
    // .el-tabs__item {
    //     padding: 0 20px;
    //     height: 40px;
    //     -webkit-box-sizing: border-box;
    //     box-sizing: border-box;
    //     line-height: 40px;
    //     display: inline-block;
    //     list-style: none;
    //     font-size: 14px;
    //     font-weight: 500;
    //     /* color: #303133; */
    //     position: relative;
    // }
    // .el-tab-pane {
    //     background-color: #fff;
    // }
}

</style>
