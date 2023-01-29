import { createWebHistory, createRouter } from "vue-router";
import Home from "../views/Home_h.vue";
import PostDetail from "../views/PostDetail.vue";
import MainCalendar from "../views/MainCalendar.vue";
import BoardMain from "../views/BoardMain.vue";
import BoardDetail from "../components/board/boardDetail.vue";
import BoardWrite from "../components/board/boardWrite.vue";
import BoardUpdate from "../components/board/boardUpdate.vue";
import CoinMain from "../views/coinMain.vue";

const routes = [
  {
    path: "/",
    name: "Post List",
    component: Home,
  },
  {
    path: "/about",
    name: "PostDetail",
    component: PostDetail,
  },
  {
    path: "/mainCalendar",
    name: "MainCalendar",
    component: MainCalendar,
  },
  {
    path: "/board",
    name: "Board",
    component: BoardMain,
    children:[
      {path : ":id", name: "BoardDetail", component: BoardDetail, props: true},
      {path : "write", name: "BoardWrite", component: BoardWrite},
      {path : "update", name: "BoardUpdate", component: BoardUpdate},
    ],
  },
  {
    path: "/coin",
    name: "Coin",
    component: CoinMain
  }
  // {
  //   path: "/boardDetail",
  //   name: "boardDetail",
  //   component: boardDetail,
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;